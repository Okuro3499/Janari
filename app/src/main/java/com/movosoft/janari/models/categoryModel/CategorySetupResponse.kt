package com.movosoft.janari.models.categoryModel

import com.google.gson.annotations.SerializedName

data class CategorySetupResponse(
    @SerializedName("statusCode") val statusCode: Int?,
    @SerializedName("message") val message: String?,
    @SerializedName("error") val error: String?,
    @SerializedName("id") val id: String?
)