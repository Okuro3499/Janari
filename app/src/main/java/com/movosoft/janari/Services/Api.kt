package com.movosoft.janari.Services

import com.movosoft.janari.Data.UserRequest
import com.movosoft.janari.Data.UserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface Api {
    @POST("Login")
    fun Login(
       @Body userRequest: UserRequest
    ):Call<UserResponse>

}