package com.movosoft.janari.Data

import com.google.gson.annotations.SerializedName

data class SubcategoryRequest(
    @SerializedName("MerchantID") val MerchantID:String?,
    @SerializedName("CategoryID") val CategoryID:String?,
    @SerializedName("Description") val Description:String?,
    @SerializedName("IsAdd") val IsAdd:String?
)
