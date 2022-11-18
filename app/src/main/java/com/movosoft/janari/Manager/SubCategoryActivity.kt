package com.movosoft.janari.Manager

import android.app.ProgressDialog
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.core.view.GravityCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.movosoft.janari.All.ReportsActivity
import com.movosoft.janari.Data.SubcategoryRequest
import com.movosoft.janari.Data.SubcategoryResponse
import com.movosoft.janari.LoginActivity
import com.movosoft.janari.R
import com.movosoft.janari.Services.Api
import com.movosoft.janari.Services.RetrofitClient
import com.movosoft.janari.databinding.ActivitySubCategoryBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class SubCategoryActivity : AppCompatActivity() {
    private  lateinit var  binding: ActivitySubCategoryBinding
    private val SharedPrefFile = "SharedPrefDat"
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySubCategoryBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.bottomView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener{item ->
            when(item.itemId){
                R.id.nav_menu->{
                    startActivity(Intent(this@SubCategoryActivity, ManagerMenuActivity::class.java)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_sales->{
                    startActivity(Intent(this@SubCategoryActivity,SalesActivity::class.java)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    )
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_reports->{
                    startActivity(Intent(this@SubCategoryActivity,ReportsActivity::class.java)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    )
                    return@OnNavigationItemSelectedListener true
                }

            }
            false
        })
         binding.nav.setOnClickListener {
             binding.drawerLayout.openDrawer(GravityCompat.START)
         }
        binding.navView.setNavigationItemSelectedListener(NavigationView.OnNavigationItemSelectedListener { item ->
            Log.i(ContentValues.TAG, "onNavigationItemSelected:" + item.itemId )
            when(item.itemId){
                R.id.nav_branch ->{
                    startActivity(Intent(this@SubCategoryActivity, BranchActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                    return@OnNavigationItemSelectedListener true
                    }
                R.id.nav_foodmenu->{
                    startActivity(Intent(this@SubCategoryActivity, ManagerMenuActivity::class.java)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    )
                    return@OnNavigationItemSelectedListener true

                }
                R.id.nav_hotelstaff->{
                    startActivity(Intent(this@SubCategoryActivity, StaffActivity::class.java)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    )
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_reports->{
                    startActivity(Intent(this@SubCategoryActivity, ReportsActivity::class.java)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_hotelsettings->{
                    startActivity(Intent(this@SubCategoryActivity, SettingsActivity::class.java)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    )
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_aboutapp->{
                    startActivity(Intent(this@SubCategoryActivity, AboutAppActivity::class.java)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    )
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_logout->{
                    startActivity(Intent(this@SubCategoryActivity,LoginActivity::class.java)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                    return@OnNavigationItemSelectedListener true
                }

            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            Log.i(ContentValues.TAG, "onNavigationItemSelected:nothing clicked")
            false
        })
        binding.btnsubmit.setOnClickListener {
            val merchantid = binding.merchantid1.text.toString().trim()
            if (TextUtils.isEmpty(merchantid)){
                binding.merchantid1.error = "Enter merchant ID"
                return@setOnClickListener
            }
            val categoryid = binding.categoryid1.text.toString().trim()
            if (TextUtils.isEmpty(categoryid)){
                binding.category1.error = "Enter category ID"
                return@setOnClickListener
            }
            val description =  binding.category1.text.toString().trim()
            if (TextUtils.isEmpty(description)){
                binding.category1.error = "Enter category"
                return@setOnClickListener
            }
             val add = binding.isadd1.text.toString().trim()
            if (TextUtils.isEmpty(add)){
                binding.isadd1.error = "Enter is add"
                return@setOnClickListener
            }
            btnsubmit(merchantid,categoryid,description,add)
        }

    }
    fun btnsubmit(merchantid:String, categoryid:String, description:String, add:String){
        val sharedPreferences:SharedPreferences = getSharedPreferences(SharedPrefFile, Context.MODE_PRIVATE)

        val progressDialog = ProgressDialog(this@SubCategoryActivity)
        progressDialog.setMessage("Loading ...")
        progressDialog.setCancelable(false)
        progressDialog.show()

        val editor: SharedPreferences.Editor = sharedPreferences.edit()

        val retro = RetrofitClient.getRetroClientInstance().create(Api::class.java)
        val subcategoryRequest = SubcategoryRequest(MerchantID = merchantid, CategoryID = categoryid, Description=description, IsAdd=add)
        retro.AddEditSubcategory(subcategoryRequest).enqueue(object:Callback<SubcategoryResponse>{
            override fun onResponse(
                call: Call<SubcategoryResponse>,
                response: Response<SubcategoryResponse>
            ) { if (response.isSuccessful)
                progressDialog.dismiss()
              val intent= Intent(this@SubCategoryActivity, LoginActivity::class.java)
                startActivity(intent)
                Log.e("Done", "${response.body()}")
                editor.putString("statusCode", response.body()!!.statusCode)
                editor.putString("message", response.body()!!.message)
                editor.putString("error", response.body()!!.error)
                editor.putString("id", response.body()!!.id)
                editor.apply()
            }

            override fun onFailure(call: Call<SubcategoryResponse>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
            }

        })
    }
}