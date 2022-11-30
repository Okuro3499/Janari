package com.movosoft.janari.models.categoryModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GetCategoryResponse(
    @SerializedName("statusCode") val statusCode: Int?,
    @SerializedName("message") val message: String?,
    @SerializedName("error") val error: String?,
    @SerializedName("data")
    @Expose
    var data: List<Categories>
)