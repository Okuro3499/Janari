package com.movosoft.janari.activities.manager

import android.app.Dialog
import android.app.ProgressDialog
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.movosoft.janari.R
import com.movosoft.janari.activities.manager.adapters.AdapterCategory
import com.movosoft.janari.api.ApiClient
import com.movosoft.janari.databinding.ActivityFoodCategoryBinding
import com.movosoft.janari.models.categoryModel.CategorySetup
import com.movosoft.janari.models.categoryModel.CategorySetupResponse
import com.movosoft.janari.models.GetCategory_SubCategory
import com.movosoft.janari.models.categoryModel.GetCategoryResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FoodCategoryActivity : AppCompatActivity() {
    lateinit var binding: ActivityFoodCategoryBinding
    private val sharedPrefFile = "sharedPrefData"
    private lateinit var apiClient: ApiClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoodCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        getCategories()

        val sharedPreferences: SharedPreferences = getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        apiClient = ApiClient

        binding.bottomNav.selectedItemId = R.id.menu

        binding.bottomNav.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu ->{
                    startActivity(Intent(applicationContext, ManagerMenuActivity::class.java))
                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.reports -> {
                    startActivity(Intent(applicationContext, ManagerReportsActivity::class.java))
                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.sales -> {
                    startActivity(Intent(applicationContext, ManagerSalesActivity::class.java))
                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        })

        binding.nav.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }

        binding.navView.setNavigationItemSelectedListener(NavigationView.OnNavigationItemSelectedListener { item ->
            Log.i(ContentValues.TAG, "onNavigationItemSelected: " + item.itemId)
            //TODO: set visibility
            when (item.itemId) {
                R.id.branches -> {
                    startActivity(Intent(this@FoodCategoryActivity, BranchActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.foodCategory -> {
                    startActivity(
                        Intent(
                            this@FoodCategoryActivity,
                            FoodCategoryActivity::class.java
                        )
                    )
                    return@OnNavigationItemSelectedListener true
                }
                R.id.foodSubCategory -> {
                    startActivity(
                        Intent(
                            this@FoodCategoryActivity,
                            FoodSubCategoryActivity::class.java
                        )
                    )
                    return@OnNavigationItemSelectedListener true
                }
                R.id.staff -> {
                    startActivity(Intent(this@FoodCategoryActivity, StaffActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.reports -> {
                    startActivity(
                        Intent(
                            this@FoodCategoryActivity,
                            ManagerReportsActivity::class.java
                        )
                    )
                    return@OnNavigationItemSelectedListener true
                }
                R.id.settings -> {
                    //TODO: create hotel settings activity
                    startActivity(Intent(this@FoodCategoryActivity, BranchActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.about -> {
                    //TODO: create about activity
                    startActivity(Intent(this@FoodCategoryActivity, BranchActivity::class.java))
                    return@OnNavigationItemSelectedListener true

                }
                R.id.logout -> {

                }
            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            Log.i(ContentValues.TAG, "onNavigationItemSelected: nothing clicked")
            false
        })

        binding.addNew.setOnClickListener {
            val dialog = Dialog(this@FoodCategoryActivity)
            dialog.setCancelable(false)
            dialog.setContentView(R.layout.new_category_dialog)
            dialog.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
            )

            val categoryName = dialog.findViewById(R.id.etCategory) as EditText
            val submitBtn = dialog.findViewById(R.id.submit) as AppCompatButton
            val noBtn = dialog.findViewById(R.id.cancel) as AppCompatButton

            submitBtn.setOnClickListener {
                if (TextUtils.isEmpty(categoryName.text.toString().trim())) {
                    categoryName.error = "Kindly enter a category name!"
                } else {
                    val progressDialog = ProgressDialog(this@FoodCategoryActivity)
                    progressDialog.setCancelable(false) // set cancelable to false
                    progressDialog.setMessage("Creating category...") // set message
                    progressDialog.show()


                    val categoryInfo = CategorySetup(
                        sharedPreferences.getString("companyID", "")?.toInt(),
                        categoryName.text.toString().trim(),
                        1
                    )
                    apiClient.getApiService(this).createCategory(categoryInfo).enqueue(object : Callback<CategorySetupResponse> {
                            override fun onResponse(call: Call<CategorySetupResponse>, response: Response<CategorySetupResponse>) {
                                if (response.isSuccessful) {
                                    progressDialog.dismiss()
                                    dialog.dismiss()

                                    val successDialog = Dialog(this@FoodCategoryActivity)
                                    successDialog.setCancelable(false)
                                    successDialog.setContentView(R.layout.success_dialog)
                                    successDialog.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

                                    val successText = successDialog.findViewById(R.id.successText) as TextView
                                    val ok = successDialog.findViewById(R.id.submit) as AppCompatButton

                                    successText.text = "Category created successfully"
                                    ok.setOnClickListener {
                                        successDialog.dismiss()
                                        restartApp()
                                    }

                                    successDialog.show()
                                    Log.e("Gideon", "onSuccess: ${response.body()}")

                                }
                            }

                            override fun onFailure(call: Call<CategorySetupResponse>, t: Throwable) {
                                progressDialog.dismiss()
                                Snackbar.make(it, "${t.message}", Snackbar.LENGTH_SHORT).show()
                                Log.e("Gideon", "onFailure: ${t.message}")
                            }
                        })
                }
            }

            noBtn.setOnClickListener { dialog.dismiss() }
            dialog.show()
        }
    }

    fun getCategories() {
        val sharedPreferences: SharedPreferences = getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        apiClient = ApiClient

//        val progressDialog = ProgressDialog(this@FoodCategoryActivity)
//        progressDialog.setCancelable(false) // set cancelable to false
//        progressDialog.setMessage("Fetching Categories...") // set message
//        progressDialog.show()

//        val editor: SharedPreferences.Editor = sharedPreferences.edit()

        binding.shimmerLayout.startShimmer();
        val getCategoryInfo = GetCategory_SubCategory(
            sharedPreferences.getString("companyID", "")?.toInt(),
        )
        apiClient.getApiService(this).getFoodCategories(getCategoryInfo).enqueue(object : Callback<GetCategoryResponse> {
            override fun onResponse(call: Call<GetCategoryResponse>, response: Response<GetCategoryResponse>) {
                if (response.isSuccessful) {
//                    progressDialog.dismiss()
                    binding.recyclerView.apply {
                        binding.etSearch.visibility = View.VISIBLE
                        binding.shimmerLayout.stopShimmer()
                        binding.shimmerLayout.visibility = View.GONE
                        //use this in Gari
                        val linearLayoutManager = LinearLayoutManager(this@FoodCategoryActivity)
                        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
//                        {or VERTICAL}
                        binding.recyclerView.layoutManager = linearLayoutManager
                        //upto here
//                        val carsAdapter = AdapterCategory(response.body()!!.data, this@MainActivity)
                        adapter = response.body()?.let { AdapterCategory(it.data, context) }
                        Log.d("data", response.body()?.data.toString())
                    }

                }

            }

            override fun onFailure(call: Call<GetCategoryResponse>, t: Throwable) {
//                progressDialog.dismiss()

                Log.e("Gideon", "onFailure: ${t.message}")
            }
        })
    }

    private fun restartApp() {
        recreate()
    }
}