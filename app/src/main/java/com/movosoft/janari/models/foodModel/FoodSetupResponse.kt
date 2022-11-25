package com.movosoft.janari.models.foodModel

import com.google.gson.annotations.SerializedName

data class FoodSetupResponse(
    @SerializedName("statusCode") val statusCode: Int?,
    @SerializedName("message") val message: String?,
    @SerializedName("error") val error: String?,
    @SerializedName("id") val id: String?
)