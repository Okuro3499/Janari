package com.movosoft.janari.Data

import com.google.gson.annotations.SerializedName

data class StaffRequest(
    @SerializedName("FirstName") val FirstName:String?,
    @SerializedName("LastName") val LastName:String?,
    @SerializedName("UserName") val UserName:String?,
    @SerializedName("Address") val Address:String?,
    @SerializedName("Role") val Role:String?,
    @SerializedName("BranchName") val BranchName:String?,
    @SerializedName("Email") val Email:String?,
    @SerializedName("Password") val Password:String?,
    @SerializedName("MerchantID") val MerchantID:String?,
    @SerializedName("OperationID") val OperationID:String?,
    @SerializedName("IsAdd") val IsAdd:String?
)
