package com.movosoft.janari.models.subCategoryModel

import com.google.gson.annotations.SerializedName

class GetSubCategoryResponse(
    @SerializedName("statusCode") val statusCode: Int?,
    @SerializedName("message") val message: String?,
    @SerializedName("error") val error: String?,
    @SerializedName("data") var data: List<SubCategories>
)