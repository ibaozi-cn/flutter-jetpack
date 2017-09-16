package one.hundred.lifestyle.data.server


import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection
import javax.net.ssl.HttpsURLConnection

/**
 * Created by zzy on 2017/9/16.
 */
@Suppress("UNCHECKED_CAST")
abstract class RemoteCallback<T> : Callback<T> {

    override fun onResponse(call: Call<T>, response: Response<T>) {
        when (response.code()) {
            HttpsURLConnection.HTTP_OK,
            HttpsURLConnection.HTTP_CREATED,
            HttpsURLConnection.HTTP_ACCEPTED,
            HttpsURLConnection.HTTP_NOT_AUTHORITATIVE ->
                if (response.isSuccessful && response.body() != null) {
                    onSuccess(response.body() as T)
                }
            HttpURLConnection.HTTP_UNAUTHORIZED -> onUnauthorized()

            else -> onFailed(Throwable("Default " + response.code() + " " + response.message()))
        }
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        onFailed(t)
    }

    abstract fun onSuccess(response: T)

    abstract fun onUnauthorized()

    abstract fun onFailed(throwable: Throwable)
}