package com.movosoft.janari.Data

import com.google.gson.annotations.SerializedName

data class BranchRequest(
    @SerializedName("BranchName") val BranchName:String?,
    @SerializedName("MerchantID") val MerchantID:String?,
    @SerializedName("Address") val Address:String?,
    @SerializedName("OperationID") val OperationID:String?,
    @SerializedName("IsAdd") val IsAdd:String?

)
