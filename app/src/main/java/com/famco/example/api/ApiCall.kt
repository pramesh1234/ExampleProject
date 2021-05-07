package com.famco.example.api

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiCall {

    @POST("mandobk_admin_test/api/users/shopByType")
    fun getShop(
        @Query("shop_type") shopType: String?,
        @Query("user_id") userId: String?,
        @Query("latitude") latitude: String?,
        @Query("longitude") longitude: String?
    ): Call<JsonObject>?

    @get:GET("mandobk_admin_test/api/users/shopcategory")
    val category: Call<JsonObject>?

}