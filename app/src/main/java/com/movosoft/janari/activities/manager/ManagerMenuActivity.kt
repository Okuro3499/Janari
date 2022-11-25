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
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.GravityCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.movosoft.janari.R
import com.movosoft.janari.api.ApiClient
import com.movosoft.janari.databinding.ActivityManagerMenuBinding
import com.movosoft.janari.models.foodModel.FoodSetup
import com.movosoft.janari.models.foodModel.FoodSetupResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ManagerMenuActivity : AppCompatActivity() {
    lateinit var binding: ActivityManagerMenuBinding
    private val sharedPrefFile = "sharedPrefData"
    private lateinit var apiClient: ApiClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityManagerMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        val sharedPreferences: SharedPreferences = getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        apiClient = ApiClient

        binding.bottomNav.selectedItemId = R.id.menu

        binding.bottomNav.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu -> return@OnNavigationItemSelectedListener true
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
                    startActivity(Intent(this@ManagerMenuActivity, BranchActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.foodCategory -> {
                    startActivity(Intent(this@ManagerMenuActivity, FoodSubCategoryActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.foodSubCategory -> {
                    startActivity(Intent(this@ManagerMenuActivity, FoodSubCategoryActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.staff -> {
                    startActivity(Intent(this@ManagerMenuActivity, StaffActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.reports -> {
                    startActivity(Intent(this@ManagerMenuActivity, ManagerReportsActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.settings -> {
                    //TODO: create hotel settings activity
                    startActivity(Intent(this@ManagerMenuActivity, BranchActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.about -> {
                    //TODO: create about activity
                    startActivity(Intent(this@ManagerMenuActivity, BranchActivity::class.java))
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
            val dialog = Dialog(this@ManagerMenuActivity)
            dialog.setCancelable(false)
            dialog.setContentView(R.layout.new_food_dialog)
            dialog.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

            val branch = dialog.findViewById<Spinner>(R.id.etBranch)
            val foodName = dialog.findViewById<EditText>(R.id.etFood)
            val foodCategory = dialog.findViewById<Spinner>(R.id.etFoodCategory)
            val foodSubCategory = dialog.findViewById<Spinner>(R.id.etFoodSubCategory)
            val foodPrice = dialog.findViewById<EditText>(R.id.etFoodPrice)
            val submitBtn = dialog.findViewById(R.id.submit) as AppCompatButton
            val noBtn = dialog.findViewById(R.id.cancel) as AppCompatButton

            submitBtn.setOnClickListener {
                if(branch.selectedItem.toString().trim() == "") {
                    Toast.makeText(this@ManagerMenuActivity, "Kindly choose branch", Toast.LENGTH_SHORT).show()
                } else if (TextUtils.isEmpty(foodName.text.toString().trim())) {
                    foodName.error = "Kindly enter a food name!"
                } else if(foodCategory.selectedItem.toString().trim() == "") {
                    Toast.makeText(this@ManagerMenuActivity, "Kindly choose Category", Toast.LENGTH_SHORT).show()
                } else if(foodSubCategory.selectedItem.toString().trim() == "") {
                    Toast.makeText(this@ManagerMenuActivity, "Kindly choose Sub Category", Toast.LENGTH_SHORT).show()
                }  else if (TextUtils.isEmpty(foodPrice.text.toString().trim())) {
                    foodPrice.error = "Kindly enter a food price!"
                } else {
                    val progressDialog = ProgressDialog(this@ManagerMenuActivity)
                    progressDialog.setCancelable(false) // set cancelable to false
                    progressDialog.setMessage("Creating food item...") // set message
                    progressDialog.show()

                    val foodInfo = FoodSetup(
                        "1",
                        sharedPreferences.getString("companyID", "")?.toInt(),
                        sharedPreferences.getString("branchID", "")?.toInt(),
                        foodName.text.toString().trim(),
                        foodCategory.selectedItem.toString().toInt(),
                        foodSubCategory.selectedItem.toString().toInt(),
                        1,
                        1,
                        foodPrice.text.toString().toInt(),
                        0,
                        1
                    )
                    apiClient.getApiService(this).createFoodItem(foodInfo).enqueue(object : Callback<FoodSetupResponse> {
                        override fun onResponse(call: Call<FoodSetupResponse>, response: Response<FoodSetupResponse>) {
                            if (response.isSuccessful) {
                                progressDialog.dismiss()
                                dialog.dismiss()

                                val successDialog = Dialog(this@ManagerMenuActivity)
                                successDialog.setCancelable(false)
                                successDialog.setContentView(R.layout.success_dialog)
                                successDialog.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

                                val successText = successDialog.findViewById(R.id.successText) as TextView
                                val ok = successDialog.findViewById(R.id.submit) as AppCompatButton

                                successText.text = "Food created successfully"
                                ok.setOnClickListener { successDialog.dismiss() }

                                successDialog.show()
                                Log.e("Gideon", "onSuccess: ${response.body()}")

                            }
                        }

                        override fun onFailure(call: Call<FoodSetupResponse>, t: Throwable) {
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
}