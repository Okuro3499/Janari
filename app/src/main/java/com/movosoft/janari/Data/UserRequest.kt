package com.movosoft.janari.Data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserRequest(
    @SerializedName("Email") val Email: String?,
    @SerializedName("password") val password: String?
)
