package com.movosoft.janari.models.branchModel

import com.google.gson.annotations.SerializedName

data class BranchSetupResponse(
    @SerializedName("statusCode") val statusCode: String?,
    @SerializedName("message") val message: String?,
    @SerializedName("error") val error: String?,
    @SerializedName("id") val id: String?
)