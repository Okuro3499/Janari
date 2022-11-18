package com.movosoft.janari.Data

import androidx.core.app.NotificationCompat.MessagingStyle.Message
import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("statusCode") val statusCode:String?,
    @SerializedName("message") val message:String?,
    @SerializedName("message") val error:String?,
    @SerializedName("id") val id:String?
)
