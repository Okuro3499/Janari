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
import com.movosoft.janari.Data.StaffRequest
import com.movosoft.janari.Data.StaffResponse
import com.movosoft.janari.LoginActivity
import com.movosoft.janari.R
import com.movosoft.janari.Services.Api
import com.movosoft.janari.Services.RetrofitClient
import com.movosoft.janari.databinding.ActivityNewStaffBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewStaffActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewStaffBinding

    private  val SharedPrefFiles = "sharedPrefData"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewStaffBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.nav_reports->{
                    startActivity(Intent(this@NewStaffActivity,ReportsActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_sales->{
                    startActivity(Intent(this@NewStaffActivity,SalesActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_menu->{
                    startActivity(Intent(this@NewStaffActivity,ManagerMenuActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }

            }
            false
        })
        binding.btnsubmit.setOnClickListener {
            startActivity(Intent(this@NewStaffActivity,StaffActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
        }


        binding.nav.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
        binding.navView.setNavigationItemSelectedListener(NavigationView.OnNavigationItemSelectedListener { item ->
            Log.i(ContentValues.TAG, "onNavigationItemSelected:"+ item.itemId)
            when(item.itemId){
                R.id.nav_branch->{
                    startActivity(Intent(this@NewStaffActivity,BranchActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_foodmenu->{
                    startActivity(Intent(this@NewStaffActivity, ManagerMenuActivity::class.java ))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_hotelstaff->{
                    startActivity(Intent(this@NewStaffActivity, StaffActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_reports->{
                    startActivity(Intent(this@NewStaffActivity,ReportsActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_hotelsettings->{
                    startActivity(Intent(this@NewStaffActivity,SettingsActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_aboutapp->{
                    startActivity(Intent(this@NewStaffActivity,AboutAppActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_logout->{
                    startActivity(Intent(this@NewStaffActivity,LoginActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }

            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            Log.i(ContentValues.TAG, "onNavigationItemSeleected:nothing clicked")
            false
        })

        binding.btnsubmit.setOnClickListener {
            val firstname = binding.edtfirstname.text.toString().trim()
            if (TextUtils.isEmpty(firstname)){
                binding.edtfirstname.error = "Enter firstname"
                return@setOnClickListener
            }
            val lastname = binding.edtlastname.text.toString().trim()
            if (TextUtils.isEmpty(lastname)){
                binding.edtlastname.error = "Enter lastname"
                return@setOnClickListener
            }
            val username = binding.edtusername.text.toString().trim()
            if (TextUtils.isEmpty(username)){
                binding.edtusername.error = "Enter username"
                return@setOnClickListener
            }
             val address = binding.edtaddress.text.toString().trim()
            if (TextUtils.isEmpty(address)){
                binding.edtaddress.error = "Enter address"
                return@setOnClickListener
            }
            val role = binding.roleassigned1.editText?.text.toString().trim()
            if(TextUtils.isEmpty(role)){
                binding.roleassigned1.error = "Select role"
                return@setOnClickListener
            }
            val branch = binding.branchassigned1.editText?.text.toString().trim()
            if (TextUtils.isEmpty(branch)){
                binding.branchassigned1.error = "Select branch"
                return@setOnClickListener
            }
            val email =binding.edtemail.text.toString().trim()
            if (TextUtils.isEmpty(email)){
                binding.edtemail.error = "Enter email"
                return@setOnClickListener
            }
            val password = binding.edtpassword.text.toString().trim()
            if(TextUtils.isEmpty(password)){
                binding.edtpassword.error = "Enter  Password"
                return@setOnClickListener
            }
            val id =binding.merchantid1.text.toString().trim()
            if(TextUtils.isEmpty(id)){
                binding.merchantid1.error = "select merchant ID"
                return@setOnClickListener
            }
            val  operatorid = binding.staffid.text.toString().trim()
            if (TextUtils.isEmpty(operatorid)){
                binding.staffid1.error = "Select operator ID"
                return@setOnClickListener
            }
            val add = binding.isadd1.text.toString().trim()
            if (TextUtils.isEmpty(add)){
                binding.isadd1.error = "Enter isadd"
                return@setOnClickListener
            }
            btnsubmit(firstname,lastname,username,address,role,branch,email,password,id,
            operatorid,add)
        }


    }
    fun btnsubmit(firstname:String, lastname:String, username:String,address:String, role:String, branch:String,
                  email:String,password:String,id:String,operatorid:String, add:String){

        val sharedPreferences: SharedPreferences = getSharedPreferences(SharedPrefFiles, Context.MODE_PRIVATE)


        val prograssDialog = ProgressDialog(this@NewStaffActivity)
        prograssDialog.setMessage("Loading...")
        prograssDialog.setCancelable(false)
        prograssDialog.show()

        val editor: SharedPreferences.Editor=sharedPreferences.edit()

        val retro = RetrofitClient.getRetroClientInstance().create(Api::class.java)
        val staffRequest = StaffRequest(FirstName = firstname, LastName = lastname, UserName = username, Address = address, Role = role,
        BranchName = branch, Email = email, Password = password, MerchantID = id, OperationID = operatorid,IsAdd = add)
        retro.AddEditUser(staffRequest).enqueue(object : Callback<StaffResponse>{
            override fun onResponse(call: Call<StaffResponse>, response: Response<StaffResponse>) {
                if (response.isSuccessful)
                    prograssDialog.dismiss()

                val intent=Intent(this@NewStaffActivity, BranchActivity::class.java)
                startActivity(intent)

                Log.i("Done", "onSuccess ${response.body()}")
                editor.putString("statuscode", response.body()!!.statusCode)
                editor.putString("message", response.body()!!.message)
                editor.putString("error", response.body()!!.error)
                editor.putString("id", response.body()!!.id)
                editor.apply()
            }

            override fun onFailure(call: Call<StaffResponse>, t: Throwable) {
             prograssDialog.dismiss()
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
            }

        })




    }
}