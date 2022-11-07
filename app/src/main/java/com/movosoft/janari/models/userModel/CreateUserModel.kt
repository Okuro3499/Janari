package com.movosoft.janari.models.userModel

import com.google.gson.annotations.SerializedName

data class CreateUserModel(
    @SerializedName("FirstName") val FirstName: String?,
    @SerializedName("LastName") val LastName: String?,
    @SerializedName("UserName") val UserName: String?,
    @SerializedName("Address") val Address: String?,
    @SerializedName("Role") val Role: String?,
    @SerializedName("BranchName") val BranchName: String?,
    @SerializedName("Email") val Email: String?,
    @SerializedName("Password") val Password: String?,
    @SerializedName("MerchantID") val MerchantID: Int?,
    @SerializedName("OperatorID") val OperatorID: String?,
    @SerializedName("IsAdd") val IsAdd: Int?
)