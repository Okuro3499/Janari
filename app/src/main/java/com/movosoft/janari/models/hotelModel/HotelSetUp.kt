package com.movosoft.janari.models.hotelModel

import com.google.gson.annotations.SerializedName

data class HotelSetUp(
    @SerializedName("MerchantName") val MerchantName: String?,
    @SerializedName("Email") val Email: String?,
    @SerializedName("TotalBranches") val TotalBranches: Int?,
    @SerializedName("PhoneNumber") val PhoneNumber: String?,
    @SerializedName("MobileNo") val MobileNo: String?,
    @SerializedName("Address") val Address: String?,
    @SerializedName("OperatorID") val OperatorID: String?,
    @SerializedName("IsAdd") val IsAdd: Int?,
)
