package com.pape.net

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class InterceptorModifyRequest(private vararg val modifiers: (original: Request, builder: Request.Builder) -> Boolean) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val rb = request.newBuilder()
        modifiers.forEach { it(request, rb) }
        return chain.proceed(rb.build())
    }

}