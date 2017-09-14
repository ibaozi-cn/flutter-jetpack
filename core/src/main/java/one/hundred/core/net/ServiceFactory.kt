package one.hundred.core.net

import android.content.Context
import com.google.gson.Gson
import com.readystatesoftware.chuck.ChuckInterceptor
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
object ServiceFactory {

    private fun getLogInterceptor(): Interceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    fun <T> createRetrofitService(BASE_URL: String, clazz: Class<T>, context: Context): T {
        val builder = OkHttpClient.Builder().
                addInterceptor(getLogInterceptor()).
                addInterceptor(ChuckInterceptor(context)).
                readTimeout(100000, TimeUnit.SECONDS).
                connectTimeout(100000, TimeUnit.SECONDS)
        val retro = Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .client(builder.build()).build()
        return retro.create(clazz)
    }
}