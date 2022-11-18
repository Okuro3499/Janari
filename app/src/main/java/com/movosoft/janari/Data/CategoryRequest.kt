package com.movosoft.janari.Data

import com.google.gson.annotations.SerializedName

data class CategoryRequest(
    @SerializedName("MerchantID") val MerchantID:String?,
    @SerializedName("Description") val Description:String?,
    @SerializedName("IsAdd") val IsAdd:String?
    )
