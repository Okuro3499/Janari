package com.movosoft.janari.Data

import com.google.gson.annotations.SerializedName

data class ProductRequest(
    @SerializedName("ProductCode") val ProductCode:String?,
    @SerializedName("MerchantID") val MerchantID:String?,
    @SerializedName("BranchID") val BranchID:String?,
    @SerializedName("ProductName") val ProductName:String?,
    @SerializedName("CategoryID") val CategoryID:String?,
    @SerializedName("SubCategoryID") val SubCategoryID:String?,
    @SerializedName("Quantity") val Quantity:String?,
    @SerializedName("CostPrice") val CostPrice:String?,
    @SerializedName("SellingPrice") val SellingPrice:String?,
    @SerializedName("Discount") val Discount:String?,
    @SerializedName("IsAdd") val IsAdd:String?
)
