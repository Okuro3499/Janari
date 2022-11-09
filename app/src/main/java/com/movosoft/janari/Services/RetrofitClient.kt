package com.movosoft.janari.Services

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient{
    fun getRetroClientInstance(): Retrofit{
        val gson = GsonBuilder().setLenient().create()
        return Retrofit.Builder()
            .baseUrl("http://edrikeddie-001-site1.itempurl.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}