
package one.hundred.core.net

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class InterceptorModifyRequest(vararg val modifiers: RequestModifier) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val rb = request.newBuilder()
        modifiers.forEach { it.modify(request, rb) }
        return chain.proceed(rb.build())
    }

    interface RequestModifier {
        fun modify(original: Request, builder: Request.Builder): Boolean
    }
}