package com.movosoft.janari.Data

import com.google.gson.annotations.SerializedName

data class MerchantRequest(
@SerializedName("MerchantName") val MerchantName:String?,
@SerializedName("Email") val Email:String?,
@SerializedName("TotalBranches") val TotalBranches:String?,
@SerializedName("PhoneNumber") val PhoneNumber:String?,
@SerializedName("MobileNo") val MobileNo:String?,
@SerializedName("Address") val Address:String?,
@SerializedName("OperatorID") val OperatorID:String?,
@SerializedName("IsAdd") val IsAdd:String?,
)

