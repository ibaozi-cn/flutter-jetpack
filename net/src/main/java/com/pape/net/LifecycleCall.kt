package com.pape.net

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LifecycleRegistry
import android.content.Context
import android.util.Log
import retrofit2.Response
import java.io.IOException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type


/**
 * author: zhangzhanyong
 * created on: 2017/12/19 下午5:55
 * description:
 */
interface LifecycleCall<T> : LifecycleAdapter {
    fun cancel()
    fun enqueue(callback: LifecycleCallback<T>)
    fun clone(): LifecycleCall<T>
    fun enableCancel(): Boolean
    override fun onDestroy() {
        if (BuildConfig.DEBUG)
            Log.d("net", "onDestroy")
        if (enableCancel())
            cancel()
    }
}


interface LifecycleCallback<T> {

    /** Called for [200, 300) responses.  */
    fun success(response: Response<T>)

    /** Called for 401 responses.  */
    fun unauthenticated(response: Response<*>)

    /** Called for [400, 500) responses, except 401.  */
    fun clientError(response: Response<*>)

    /** Called for [500, 600) response.  */
    fun serverError(response: Response<*>)

    /** Called for network errors while making the call.  */
    fun networkError(e: IOException)

    /** Called for unexpected errors while making the call.  */
    fun unexpectedError(t: Throwable)

}

class LifecycleCallAdapter<T>(private val call: Call<T>, private val enableCancel: Boolean) : LifecycleCall<T> {

    override fun enableCancel() = enableCancel

    override fun cancel() {
        call.cancel()
    }

    override fun enqueue(callback: LifecycleCallback<T>) {
        call.enqueue(object : Callback<T> {

            override fun onResponse(call: Call<T>, response: Response<T>) {
                val code = response.code()
                when (code) {
                    in 200..299 -> callback.success(response)
                    401 -> callback.unauthenticated(response)
                    in 400..499 -> callback.clientError(response)
                    in 500..599 -> callback.serverError(response)
                    else -> callback.unexpectedError(RuntimeException("Unexpected response " + response))
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                if (t is IOException) {
                    callback.networkError(t)
                } else {
                    callback.unexpectedError(t)
                }
            }
        })
    }

    override fun clone(): LifecycleCall<T> {
        return LifecycleCallAdapter<T>(call.clone(), enableCancel)
    }

}

class LifecycleCallAdapterFactory(private val context: Context, private val enableCancel: Boolean = true) : CallAdapter.Factory() {

    override fun get(returnType: Type, annotations: Array<Annotation>, retrofit: Retrofit): CallAdapter<*, *>? {

        if (CallAdapter.Factory.getRawType(returnType) != LifecycleCall::class.java) {
            return null
        }

        if (returnType !is ParameterizedType) {
            throw IllegalStateException(
                    "MyCall must have generic type (e.g., MyCall<ResponseBody>)")
        }
        val responseType = CallAdapter.Factory.getParameterUpperBound(0, returnType)

        if (context is LifecycleOwner) {
            if (BuildConfig.DEBUG)
                Log.d("net", "context is LifecycleOwner")
            val lifecycleRegistry = context.lifecycle
            if (lifecycleRegistry is LifecycleRegistry) {
                Log.d("net", "lifecycle is LifecycleRegistry")
                return ErrorHandlingCallAdapter<Any>(responseType, lifecycleRegistry, enableCancel)
            }
        }
        return ErrorHandlingCallAdapter<Any>(responseType, enableCancel = enableCancel)
    }

    private class ErrorHandlingCallAdapter<R> internal constructor(private val responseType: Type, val lifecycleRegistry: LifecycleRegistry? = null, val enableCancel: Boolean) : CallAdapter<R, LifecycleCall<R>> {

        override fun responseType(): Type {
            return responseType
        }

        override fun adapt(call: Call<R>): LifecycleCall<R> {
            return LifecycleCallAdapter(call, enableCancel).also {
                lifecycleRegistry?.addObserver(it)
            }
        }
    }

}