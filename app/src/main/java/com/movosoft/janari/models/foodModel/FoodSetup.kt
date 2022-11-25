package com.movosoft.janari.models.foodModel

import com.google.gson.annotations.SerializedName

data class FoodSetup(
    @SerializedName("ProductCode") val ProductCode: String?,
    @SerializedName("MerchantID") val MerchantID: Int?,
    @SerializedName("BranchID") val BranchID: Int?,
    @SerializedName("ProductName") val ProductName: String?,
    @SerializedName("CategoryID") val CategoryID: Int?,
    @SerializedName("SubCategoryID") val SubCategoryID: Int?,
    @SerializedName("Quantity") val Quantity: Int?,
    @SerializedName("CostPrice") val CostPrice: Int?,
    @SerializedName("SellingPrice") val SellingPrice: Int?,
    @SerializedName("Discount") val Discount: Int?,
    @SerializedName("IsAdd") val IsAdd: Int?,
)