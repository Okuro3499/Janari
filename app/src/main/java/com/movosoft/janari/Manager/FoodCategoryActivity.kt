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
import com.movosoft.janari.Data.CategoryRequest
import com.movosoft.janari.Data.CategoryResponse
import com.movosoft.janari.LoginActivity
import com.movosoft.janari.R
import com.movosoft.janari.Services.Api
import com.movosoft.janari.Services.RetrofitClient
import com.movosoft.janari.databinding.ActivityFoodCategoryBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FoodCategoryActivity : AppCompatActivity() {
    private lateinit var  binding:ActivityFoodCategoryBinding
    val sharedPrefFiles = "SharedPrefData"
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityFoodCategoryBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.bottomView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.nav_menu->{
                    startActivity(Intent(this@FoodCategoryActivity, ManagerMenuActivity::class.java)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    )
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_sales->{
                    startActivity(Intent(this@FoodCategoryActivity,SalesActivity::class.java)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    )
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_reports->{
                    startActivity(Intent(this@FoodCategoryActivity, ReportsActivity::class.java)
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
            Log.i(ContentValues.TAG, "onNavigationItemSelected" +item.itemId)
            when(item.itemId){
                R.id.nav_branch->{
                    startActivity(Intent(this@FoodCategoryActivity, BranchActivity::class.java)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_foodmenu->{
                    startActivity(Intent(this@FoodCategoryActivity, ManagerMenuActivity::class.java)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    )
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_hotelstaff->{
                    startActivity(Intent(this@FoodCategoryActivity, StaffActivity::class.java)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    )
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_reprts->{
                    startActivity(Intent(this@FoodCategoryActivity,ReportsActivity::class.java)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_hotelsettings->{
                    startActivity(Intent(this@FoodCategoryActivity, SettingsActivity::class.java)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    )
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_aboutapp->{
                    startActivity(Intent(this@FoodCategoryActivity, AboutAppActivity::class.java)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    )
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_logout->{
                    startActivity(Intent(this@FoodCategoryActivity, LoginActivity::class.java)
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
            val description = binding.category1.text.toString().trim()
            if (TextUtils.isEmpty(description)){
                binding.category1.error = "Enter description"
                return@setOnClickListener
            }
            val add = binding.isadd1.text.toString().trim()
            if (TextUtils.isEmpty(add)){
                binding.isadd1.error = "Enter is add"
                return@setOnClickListener
            }
            btnsubmit(merchantid,description,add)
        }
    }
    fun btnsubmit(merchantid:String, description:String, add:String){
        val sharedPreferences:SharedPreferences = getSharedPreferences(sharedPrefFiles, Context.MODE_PRIVATE)

        val progressDialog= ProgressDialog(this@FoodCategoryActivity)
        progressDialog.setMessage("Loading...")
        progressDialog.setCancelable(false)
        progressDialog.show()

        val editor: SharedPreferences.Editor = sharedPreferences.edit()

        val retro = RetrofitClient.getRetroClientInstance().create(Api::class.java)
        val categoryRequest = CategoryRequest(MerchantID = merchantid, Description = description, IsAdd = add)
        retro.AddEditCategory(categoryRequest).enqueue(object : Callback<CategoryResponse>{
            override fun onResponse(
                call: Call<CategoryResponse>,
                response: Response<CategoryResponse>
            ) {
                if (response.isSuccessful)
                    progressDialog.dismiss()
                val intent=Intent(this@FoodCategoryActivity,LoginActivity::class.java)
                startActivity(intent)

                Log.i("Done", "${response.body()}")
                editor.putString("statuCode", response.body()!!.statusCode)
                editor.putString("message", response.body()!!.message)
                editor.putString("error", response.body()!!.error)
                editor.putString("id", response.body()!!.id)
                editor.apply()

            }

            override fun onFailure(call: Call<CategoryResponse>, t: Throwable) {
               Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
            }

        })
    }
}