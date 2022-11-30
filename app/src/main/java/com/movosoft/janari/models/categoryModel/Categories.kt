package com.movosoft.janari.models.categoryModel

import com.google.gson.annotations.SerializedName

data class Categories(
    @SerializedName("categoryID") val categoryID: Int?,
    @SerializedName("merchantID") val merchantID: Int?,
    @SerializedName("branchID") val branchID: Int?,
    @SerializedName("description") val description: String?,
    @SerializedName("isActive") val isActive: String?,
    @SerializedName("createdBy") val createdBy: String?,
    @SerializedName("createdOn") val createdOn: String?,
    @SerializedName("modifiedBy") val modifiedBy: String?,
    @SerializedName("modifiedOn") val modifiedOn: String?,
    @SerializedName("deletedBy") val deletedBy: String?,
    @SerializedName("deletedOn") val deletedOn: String?,
    @SerializedName("updateCount") val updateCount: String?,
    @SerializedName("operatorID") val operatorID: String?,
    @SerializedName("isAdd") val isAdd: String?,
)