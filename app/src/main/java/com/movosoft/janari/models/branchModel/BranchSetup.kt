package com.movosoft.janari.models.branchModel

import com.google.gson.annotations.SerializedName

data class BranchSetup(
    @SerializedName("BranchName") val BranchName: String?,
    @SerializedName("MerchantID") val MerchantID: Int?,
    @SerializedName("Address") val Address: String?,
    @SerializedName("OperatorID") val OperatorID: String?,
    @SerializedName("IsAdd") val IsAdd: Int?
)