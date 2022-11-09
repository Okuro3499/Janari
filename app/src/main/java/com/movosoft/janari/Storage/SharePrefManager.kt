package com.movosoft.janari.Storage

import android.content.Context
import com.movosoft.janari.Data.UserRequest

//class SharePrefManager  private constructor(private val mCtx: Context) {

//    val isLoggedIn: Boolean
//    get() {
//        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
//        return sharedPreferences.getInt("id", -1) !=-1
//    }
//    val user: UserRequest
//    get() {
//        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
//        return UserRequest(
//            sharedPreferences.getString("email", null),
//            sharedPreferences.getString("pass")
//        )
//    }
//}