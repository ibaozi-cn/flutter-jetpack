package com.pape.net

import android.content.Context
import com.google.gson.Gson
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @project: KotlinMvpDemo
 * @description:网络管理
 * @author zzy05 zhangzhanyong@feinno.com
 * @date 2017/6/27 11:52
 * @updateUser zzy05
 * @update 2017/6/27 11:52
 * @version V1.0
 */
class ApiFactory(private val context: Context) {

    private val okHttpClient by lazy {
        OkHttpClient.Builder().readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .cache(Cache(context.cacheDir, 50 * 1024 * 1024)) // 设置缓存大小
                .addInterceptor(ChuckInterceptor(context))
                .build()
    }

    /**
     * 其他业务Api
     */
    fun <T> createApi(clazz: Class<T>, endpoint: String, interceptorModifyRequest: InterceptorModifyRequest = InterceptorModifyRequest()): T {
        val retrofit = Retrofit.Builder()
                .client(okHttpClient.also {
                    it.newBuilder().addInterceptor(interceptorModifyRequest)
                })
                .baseUrl(endpoint)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(LifecycleCallAdapterFactory(context))
                .build()
        return retrofit.create(clazz)
    }

    companion object {
        private var sInstance: ApiFactory? = null
        fun instance(context: Context): ApiFactory =
                sInstance ?: synchronized(ApiFactory::class.java) {
                    sInstance ?: ApiFactory(context).also {
                        sInstance = it
                    }
                }
    }
}
