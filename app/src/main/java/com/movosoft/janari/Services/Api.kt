package com.movosoft.janari.Services

import com.movosoft.janari.Data.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface Api {
    @POST("Janari/Security/Login")
    fun Login(
       @Body userRequest: UserRequest
    ):Call<UserResponse>

    @POST("Janari/Administration/MerchantSetup")
    fun MerchantSetup(
        @Body merchantRequest: MerchantRequest
    ):Call<MerchantResponse>

    @POST("Janari/Administration/BranchSetup")
    fun BranchSetup(
        @Body branchRequest: BranchRequest
    ):Call<BranchResponse>

    @POST("Janari/Administration/AddEditUser")
    fun AddEditUser(
        @Body staffRequest: StaffRequest
    ):Call<StaffResponse>

    @POST("Janari/Inventory/AddEditProduct")
    fun AddEditProduct(
        @Body productRequest: ProductRequest
    ):Call<ProductResponse>

    @POST("Janari/Inventory/AddEditSubcategory")
    fun AddEditSubcategory(
        @Body subcategoryRequest: SubcategoryRequest
    ):Call<SubcategoryResponse>

    @POST("Janari/Inventory/AddEditCategory")
    fun AddEditCategory(
        @Body categoryRequest: CategoryRequest
    ):Call<CategoryResponse>

}