package com.example.hidebottom.http

import com.example.hidebottom.bean.CatBean
import com.example.hidebottom.bean.GrilBean
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * <pre>
 *     author : admin
 *     e-mail : xxx@xx
 *     time   : 2021/09/29
 *     desc   :
 *     version: 1.0
 * </pre>
 */
interface ApiService {

    //@Header("user-agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.82 Safari/537.36")
    @GET(".")
    suspend fun getPictureSouce(
        @Query("key") key: String,
        @Query("q") q: String,
        @Query("per_page") per_page: Int,
        @Query("page") page: Int,
        @Query("pretty") pretty: Boolean
    ): CatBean

    @GET("v2/data/category/Girl/type/Girl/page/1/count/10")
    suspend fun getPictureGirl(): GrilBean
}