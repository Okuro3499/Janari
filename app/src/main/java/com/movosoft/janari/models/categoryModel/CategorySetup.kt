package com.movosoft.janari.models.categoryModel

import com.google.gson.annotations.SerializedName

data class CategorySetup(
    @SerializedName("MerchantID") val MerchantID: Int?,
    @SerializedName("Description") val Description: String?,
    @SerializedName("IsAdd") val IsAdd: Int?
)