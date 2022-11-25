package com.movosoft.janari.models.subCategoryModel

import com.google.gson.annotations.SerializedName

data class SubCategorySetup(
    @SerializedName("MerchantID") val MerchantID: Int?,
    @SerializedName("CategoryID") val CategoryID: Int?,
    @SerializedName("Description") val Description: String?,
    @SerializedName("IsAdd") val IsAdd: Int?
)