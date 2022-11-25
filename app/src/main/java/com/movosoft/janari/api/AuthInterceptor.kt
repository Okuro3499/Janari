package com.movosoft.janari.api

import android.content.Context
import android.content.SharedPreferences
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(context: Context) : Interceptor {
    private val sharedPrefFile = "sharedPrefData"
    private val sessionManager = SessionManager(context)

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
//        val sharedPreferences: SharedPreferences = sessionManager.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)

        // If token has been saved, add it to the request
        sessionManager.fetchAuthToken()?.let {
            requestBuilder
                .addHeader("Authorization", "Bearer $it")
                .addHeader("Email", "gideon.okuro@kibandaski.com")
                .addHeader("TokenID", it)

        }
        return chain.proceed(requestBuilder.build())
    }
}