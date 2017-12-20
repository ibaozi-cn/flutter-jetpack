package one.hundred.lifestyle.data.server

import com.pape.net.LifecycleCall
import com.pape.net.LifecycleCallback
import one.hundred.lifestyle.data.bean.AnimeResponse
import one.hundred.lifestyle.data.bean.Test
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * Created by zzy on 2017/9/16.
 */
interface ApiService {

    @GET("/anime/{id}")
    fun getAnime(@Path("id") id : Int): LifecycleCall<AnimeResponse>

}

const val BASE_URL = "http://api.jikan.me/"