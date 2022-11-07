package com.movosoft.janari.models.hotelModel

import com.google.gson.annotations.SerializedName

data class HotelSetUpResponse(
    @SerializedName("statusCode") val statusCode: String?,
    @SerializedName("message") val message: String?,
    @SerializedName("error") val error: String?,
    @SerializedName("id") val id: String?,
)
