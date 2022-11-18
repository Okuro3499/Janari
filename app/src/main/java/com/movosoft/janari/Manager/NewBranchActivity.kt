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
import com.movosoft.janari.Data.BranchRequest
import com.movosoft.janari.Data.BranchResponse
import com.movosoft.janari.LoginActivity
import com.movosoft.janari.R
import com.movosoft.janari.Services.Api
import com.movosoft.janari.Services.RetrofitClient
import com.movosoft.janari.databinding.ActivityNewBranchBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewBranchActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewBranchBinding
    private val SharedprefFiles = "SharedPrefData"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewBranchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (actionBar?.isShowing == true) {
            actionBar!!.hide()
        }
        binding.bottomView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_foodmenu -> {
                    startActivity(Intent(this@NewBranchActivity, ManagerMenuActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_sales -> {
                    startActivity(Intent(this@NewBranchActivity, SalesActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_reports -> {
                    startActivity(Intent(this@NewBranchActivity, ReportsActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        })
        binding.btnsubmit.setOnClickListener {

        }
        binding.nav.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
        binding.navView.setNavigationItemSelectedListener(NavigationView.OnNavigationItemSelectedListener { item ->
            Log.i(ContentValues.TAG, "onNavigationItemSelected:" + item.itemId)
            when (item.itemId) {
                R.id.nav_branch -> {
                    startActivity(Intent(this@NewBranchActivity, BranchActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_foodmenu -> {
                    startActivity(Intent(this@NewBranchActivity, ManagerMenuActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_reports -> {
                    startActivity(Intent(this@NewBranchActivity, ReportsActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_aboutapp -> {
                    startActivity(Intent(this@NewBranchActivity, AboutAppActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_logout -> {
                    startActivity(Intent(this@NewBranchActivity, LoginActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_hotelstaff -> {
                    startActivity(Intent(this@NewBranchActivity, StaffActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_hotelsettings -> {
                    startActivity(Intent(this@NewBranchActivity, SettingsActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }

            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            Log.i(ContentValues.TAG, "onNavigationItemSelected: nothing clicked")
            false
        })

        binding.btnsubmit.setOnClickListener {
            val name = binding.branchname1.text.toString().trim()

            if (TextUtils.isEmpty(name)) {
                binding.branchname1.error = "Enter hotel name"
                return@setOnClickListener

            }
            val id = binding.merchantid1.text.toString().trim()
            if (TextUtils.isEmpty(id)) {
                binding.merchantid1.error = "Enter MerchantID"
                return@setOnClickListener
            }
            val address = binding.address1.text.toString().trim()
            if (TextUtils.isEmpty(address)) {
                binding.address.error = "Enter Address"
                return@setOnClickListener
            }
            val operator = binding.operatorid1.text.toString()
            if (TextUtils.isEmpty(operator)) {
                binding.operatorid1.error = "Enter operatorID"
                return@setOnClickListener
            }
            val add = binding.issadd1.text.toString().trim()
            if (TextUtils.isEmpty(add)) {
                binding.issadd1.error = "Error IsAdd"
            }
            btnsubmit(name, id, address, operator, add)
        }
    }

        fun btnsubmit(name:String, id:String, address:String, operator:String, add:String  ){
            val sharedPreferences: SharedPreferences =  getSharedPreferences(SharedprefFiles, Context.MODE_PRIVATE)

            val progressDialog = ProgressDialog(this@NewBranchActivity)
            progressDialog.setMessage("Loading...")
            progressDialog.setCancelable(false)
            progressDialog.show()

            val editor: SharedPreferences.Editor= sharedPreferences.edit()

            val retro = RetrofitClient.getRetroClientInstance().create(Api::class.java)
            val branchRequest = BranchRequest(BranchName = name, MerchantID = id, Address = address, OperationID = operator, IsAdd = add)
            retro.BranchSetup(branchRequest).enqueue(object :Callback<BranchResponse>{
                override fun onResponse(
                    call: Call<BranchResponse>,
                    response: Response<BranchResponse>
                ) {
                    if (response.isSuccessful)
                        progressDialog.dismiss()
                    val intent= Intent(this@NewBranchActivity, BranchActivity::class.java)
                    startActivity(intent)

                    Log.i("Done", "onSuccess ${response.body()}")
                    editor.putString("statusCode", response.body()!!.statusCode)
                    editor.putString("message", response.body()!!.message)
                    editor.putString("error" , response.body()!!.error)
                    editor.putString("id", response.body()!!.id)
                    editor.apply()
                }

                override fun onFailure(call: Call<BranchResponse>, t: Throwable) {
                    progressDialog.dismiss()
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                }

            })
        }



}