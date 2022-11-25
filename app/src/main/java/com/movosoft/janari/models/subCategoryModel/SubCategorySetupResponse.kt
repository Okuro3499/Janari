package com.movosoft.janari.models.subCategoryModel

import com.google.gson.annotations.SerializedName

data class SubCategorySetupResponse(
    @SerializedName("statusCode") val statusCode: Int?,
    @SerializedName("message") val message: String?,
    @SerializedName("error") val error: String?,
    @SerializedName("id") val id: String?
)