package com.pape.net

import kotlinx.coroutines.experimental.CancellableContinuation
import kotlinx.coroutines.experimental.suspendCancellableCoroutine
import retrofit2.Response
import java.io.IOException

/**
 * author: zhangzhanyong
 * created on: 2017/12/19 下午4:47
 * description:
 */

suspend fun <T> LifecycleCall<T>.await(): T = suspendCancellableCoroutine { continuation ->

    continuation.invokeOnCompletion { if (continuation.isCancelled) cancel() }

    val callback = object : LifecycleCallback<T> {

        override fun success(response: Response<T>) = continuation.tryToResume {
            response.body() ?: throw IllegalStateException("Response body is null")
        }

        override fun unauthenticated(response: Response<*>) = continuation.tryToResume { throw Exception("没有权限") }

        override fun clientError(response: Response<*>) = continuation.tryToResume { throw Exception("客户端异常") }

        override fun serverError(response: Response<*>) = continuation.tryToResume { throw Exception("服务端异常") }

        override fun networkError(e: IOException) = continuation.tryToResume { throw e }

        override fun unexpectedError(t: Throwable) = continuation.tryToResume { throw t }

    }
    enqueue(callback)
}

private inline fun <T> CancellableContinuation<T>.tryToResume(getter: () -> T) {
    isActive || return
    try {
        resume(getter())
    } catch (exception: Throwable) {
        resumeWithException(exception)
    }
}


