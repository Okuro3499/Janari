package com.movosoft.janari.Services

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient{
    fun getRetroClientInstance(): Retrofit{
        val logger = HttpLoggingInterceptor().apply {
            level=HttpLoggingInterceptor.Level.BODY
        }
        val client = OkHttpClient.Builder()
            .addInterceptor(logger)
        val gson = GsonBuilder().setLenient().create()
        return Retrofit.Builder()
            .baseUrl("http://edrikeddie-001-site1.itempurl.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client.build())
            .build()
    }
}