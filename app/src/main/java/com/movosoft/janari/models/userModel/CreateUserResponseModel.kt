package com.movosoft.janari.models.userModel

import com.google.gson.annotations.SerializedName

data class CreateUserResponseModel(
    @SerializedName("statusCode") val statusCode: String?,
    @SerializedName("message") val message: String?,
    @SerializedName("error") val error: String?,
    @SerializedName("id") val id: Int?
)