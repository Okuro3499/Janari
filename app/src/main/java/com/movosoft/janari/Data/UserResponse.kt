package com.movosoft.janari.Data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserResponse {
    @SerializedName("userId")
    @Expose
    var userID:String?= null
    @SerializedName("firstName")
    @Expose
    var firstName:String?= null
    @SerializedName("lastName")
    @Expose
    var lastName:String?= null
    @SerializedName("userName")
    @Expose
    var userName:String?= null
    @SerializedName("companyID")
    @Expose
    var companyID:String?= null
    @SerializedName("companyName")
    @Expose
    var companyName:String?= null
    @SerializedName("role")
    @Expose
    var role:String?= null
    @SerializedName("branchID")
    @Expose
    var branchId:String?= null
    @SerializedName("industryType")
    @Expose
    var industryType:String?= null
    @SerializedName("appReleaseVersion")
    @Expose
    var appReleaseVersion:String?= null
    @SerializedName("tokenID")
    @Expose
    var tokenID:String?= null
    @SerializedName("tokenExpiry")
    @Expose
    var tokenExpiry:String?= null
    @SerializedName("statusCode")
    @Expose
    var statusCode:String?= null
    @SerializedName("message")
    @Expose
    var message:String?= null
    @SerializedName("error")
    @Expose
    var error:String?= null

}