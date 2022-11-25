package com.movosoft.janari.api

import com.movosoft.janari.models.branchModel.BranchSetup
import com.movosoft.janari.models.branchModel.BranchSetupResponse
import com.movosoft.janari.models.categoryModel.CategorySetup
import com.movosoft.janari.models.categoryModel.CategorySetupResponse
import com.movosoft.janari.models.foodModel.FoodSetup
import com.movosoft.janari.models.foodModel.FoodSetupResponse
import com.movosoft.janari.models.hotelModel.HotelSetUp
import com.movosoft.janari.models.hotelModel.HotelSetUpResponse
import com.movosoft.janari.models.subCategoryModel.SubCategorySetup
import com.movosoft.janari.models.subCategoryModel.SubCategorySetupResponse
import com.movosoft.janari.models.userModel.CreateUserModel
import com.movosoft.janari.models.userModel.CreateUserResponseModel
import com.movosoft.janari.models.userModel.UserLogin
import com.movosoft.janari.models.userModel.UserLoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("Janari/Security/Login")
    fun loginUser(@Body userLogin: UserLogin?): Call<UserLoginResponse>

    @POST("Janari/Administration/MerchantSetup")
    fun createHotel(@Body hotelSetUp: HotelSetUp?): Call<HotelSetUpResponse>

    @POST("Janari/Administration/AddEditUser")
    fun createUser(@Body createUserModel: CreateUserModel?): Call<CreateUserResponseModel>

    @POST("Janari/Administration/BranchSetup")
    fun createBranch(@Body branchSetUp: BranchSetup?): Call<BranchSetupResponse>

    @POST("Janari/Inventory/AddEditCategory")
    fun createCategory(@Body categorySetup: CategorySetup?): Call<CategorySetupResponse>

    @POST("Janari/Inventory/AddEditSubcategory")
    fun createSubCategory(@Body subCategorySetup: SubCategorySetup?): Call<SubCategorySetupResponse>

    @POST("Janari/Inventory/AddEditProduct")
    fun createFoodItem(@Body foodSetup: FoodSetup?): Call<FoodSetupResponse>

}
