package one.hundred.lifestyle.data.server

import one.hundred.lifestyle.data.bean.Test
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * Created by zzy on 2017/9/16.
 */
interface ApiService{

    @GET("test/{id}")
    fun getList(@Path("id") id: Long): Call<List<Test>>

}