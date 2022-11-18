package com.movosoft.janari.Data

import android.view.textclassifier.ConversationActions.Message
import com.google.gson.annotations.SerializedName

data class SubcategoryResponse(
    @SerializedName("statusCode") val statusCode:String?,
    @SerializedName("message") val message:String?,
    @SerializedName("error") val error:String?,
    @SerializedName("id") val id:String?
    )
