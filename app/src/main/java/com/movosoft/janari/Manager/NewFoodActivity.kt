package com.movosoft.janari.Manager

import android.annotation.SuppressLint
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
import com.movosoft.janari.Data.ProductRequest
import com.movosoft.janari.Data.ProductResponse
import com.movosoft.janari.LoginActivity
import com.movosoft.janari.R
import com.movosoft.janari.Services.Api
import com.movosoft.janari.Services.RetrofitClient
import com.movosoft.janari.databinding.ActivityNewFoodBinding
import okhttp3.internal.cache2.Relay.Companion.edit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewFoodActivity : AppCompatActivity() {
    private lateinit var binding:ActivityNewFoodBinding
    private val SharedPrefFiles = "SharedPrefData"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.bottomView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener{item ->
            when(item.itemId){
                R.id.nav_menu ->{
                    startActivity(Intent(this@NewFoodActivity, ManagerMenuActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_sales->{
                    startActivity(Intent(this@NewFoodActivity,SalesActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_reports ->{
                    startActivity(Intent(this@NewFoodActivity,ReportsActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }

            }
            false
        })

        binding.nav.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
        binding.navView.setNavigationItemSelectedListener(NavigationView.OnNavigationItemSelectedListener { item ->
            Log.i(ContentValues.TAG, "onNavigationItemSelected:" + item.itemId)
            when(item.itemId){
                R.id.nav_reports ->{
                    startActivity(Intent(this@NewFoodActivity, ReportsActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_foodmenu ->{
                    startActivity(Intent(this@NewFoodActivity, ManagerMenuActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_hotelstaff->{
                    startActivity(Intent(this@NewFoodActivity, StaffActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_logout->{
                    startActivity(Intent(this@NewFoodActivity, LoginActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_hotelsettings->{
                    startActivity(Intent(this@NewFoodActivity, SettingsActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_aboutapp->{
                    startActivity(Intent(this@NewFoodActivity, AboutAppActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_branch->{
                    startActivity(Intent(this@NewFoodActivity,BranchActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }

            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            Log.i(ContentValues.TAG, "onNavigationItemSelected: nothing clicked")
            false
        })
        binding.btnsubmit.setOnClickListener {
            val productcode = binding.foodcode1.text.toString().trim()
            if(TextUtils.isEmpty(productcode)){
                binding.foodcode1.error = "Enter food code"
                return@setOnClickListener
            }
            val  merchantid = binding.merchantid1.text.toString().trim()
            if (TextUtils.isEmpty(merchantid)){
                binding.merchantid1.error = "Enter merchant ID"
                return@setOnClickListener
            }
            val branchid = binding.branchid1.text.toString().trim()
            if (TextUtils.isEmpty(branchid)){
                binding.branchid1.error = "Enter branch ID"
                return@setOnClickListener
            }
            val productname= binding.foodname1.text.toString().trim()
            if(TextUtils.isEmpty(productname)){
                binding.foodname1.error = "Enter food name"
                return@setOnClickListener
            }
            val categoryid = binding.categoryid1.text.toString().trim()
            if (TextUtils.isEmpty(categoryid)){
                binding.categoryid.error = "Enter category ID"
                return@setOnClickListener
            }
            val subcategoryid = binding.subcategoryid1.text.toString().trim()
            if (TextUtils.isEmpty(subcategoryid)){
                binding.subcategoryid1.error = "Enter sub-category ID"
                return@setOnClickListener
            }
            val quantity = binding.quantity1.text.toString().trim()
            if (TextUtils.isEmpty(quantity)){
                binding.quantity1.error = "Enter quantity"
                return@setOnClickListener
            }
            val cost = binding.costprice1.text.toString()
            if (TextUtils.isEmpty(cost)){
                binding.costprice1.error = "Enter costprice"
                return@setOnClickListener
            }
            val selling = binding.sellingprice1.text.toString().trim()
            if (TextUtils.isEmpty(selling)){
                binding.sellingprice1.error = "Enter sellingprice"
                return@setOnClickListener
            }
            val discount = binding.discount1.text.toString().trim()
            if (TextUtils.isEmpty(discount)){
                binding.discount1.error = "Enter discount"
                return@setOnClickListener
            }
            val add = binding.isadd1.text.toString().trim()
            if (TextUtils.isEmpty(add)){
                binding.isadd1.error = "Enter is add"
                return@setOnClickListener
            }
            btnsubmit(productcode, merchantid, branchid, productname, categoryid, subcategoryid, quantity, cost, selling, discount,add)

        }

    }
    fun btnsubmit(productcode:String, merchantid:String, branchid:String, productname:String, categoryid:String, subcategoryid:String, quantity:String, cost:String,
                  selling:String, discount:String, add:String){
        val sharedPreferences:SharedPreferences=getSharedPreferences(SharedPrefFiles, Context.MODE_PRIVATE)

        val progressDialog = ProgressDialog(this@NewFoodActivity)
        progressDialog.setMessage("Loading...")
        progressDialog.setCancelable(false)
        progressDialog.show()

        val editor: SharedPreferences.Editor = sharedPreferences.edit()

        val retro = RetrofitClient.getRetroClientInstance().create(Api::class.java)
        val productRequest = ProductRequest(ProductCode = productcode, MerchantID = merchantid, BranchID = branchid, ProductName = productname,
        CategoryID = categoryid, SubCategoryID = subcategoryid, Quantity = quantity, CostPrice = cost, SellingPrice = selling, Discount = discount, IsAdd = add)
        retro.AddEditProduct(productRequest).enqueue(object :Callback<ProductResponse>{
            override fun onResponse(
                call: Call<ProductResponse>,
                response: Response<ProductResponse>
            ) {
                if(response.isSuccessful)
                    progressDialog.dismiss()
                startActivity(Intent(this@NewFoodActivity, ManagerMenuActivity::class.java)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                Log.i("Done", "${response.body()}")
                editor.putString("statuscode", response.body()!!.statusCode)
                editor.putString("message", response.body()!!.message)
                editor.putString("error", response.body()!!.error)
                editor.putString("id", response.body()!!.id)
                editor.apply()

            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
            }

        })
    }
}