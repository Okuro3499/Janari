package com.movosoft.janari.models.userModel

import com.google.gson.annotations.SerializedName

data class UserLoginResponse(
    @SerializedName("userID") val userID: String?,
    @SerializedName("firstName") val firstName: String?,
    @SerializedName("lastName") val lastName: String?,
    @SerializedName("userName") val userName: String?,
    @SerializedName("companyID") val companyID: String?,
    @SerializedName("companyName") val companyName: String?,
    @SerializedName("role") val role: String?,
    @SerializedName("branchID") val branchID: String?,
    @SerializedName("industryType") val industryType: String?,
    @SerializedName("appReleaseVersion") val appReleaseVersion: String?,
    @SerializedName("tokenID") val tokenID: String?,
    @SerializedName("tokenExpiry") val tokenExpiry: String?,
    @SerializedName("statusCode") val statusCode: String?,
    @SerializedName("message") val message: String?,
    @SerializedName("error") val error: String?,
)