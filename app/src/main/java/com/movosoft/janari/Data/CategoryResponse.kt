package com.movosoft.janari.Data

import com.google.gson.annotations.SerializedName

data class CategoryResponse(
    @SerializedName("statusCode") val statusCode:String?,
    @SerializedName("message") val message:String?,
    @SerializedName("error") val error:String?,
    @SerializedName("id") val id:String?

    )
