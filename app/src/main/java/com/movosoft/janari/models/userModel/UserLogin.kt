package com.movosoft.janari.models.userModel

import com.google.gson.annotations.SerializedName

data class UserLogin(
    @SerializedName("Email") val Email: String?,
    @SerializedName("password") val password: String?
)