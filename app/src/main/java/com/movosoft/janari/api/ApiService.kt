package com.movosoft.janari.api

import com.movosoft.janari.models.userModel.UserLogin
import com.movosoft.janari.models.userModel.UserLoginResponse
import com.movosoft.janari.models.hotelModel.HotelSetUp
import com.movosoft.janari.models.hotelModel.HotelSetUpResponse
import com.movosoft.janari.models.userModel.CreateUserModel
import com.movosoft.janari.models.userModel.CreateUserResponseModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("Janari/Security/Login")
    fun loginUser(@Body userLogin: UserLogin?): Call<UserLoginResponse>

    @POST("Janari/Administration/MerchantSetup")
    fun createHotel(@Body hotelSetUp: HotelSetUp?): Call<HotelSetUpResponse>

    @POST("Janari/Administration/AddEditUser")
    fun createUser(@Body createUserModel: CreateUserModel?): Call<CreateUserResponseModel>
}