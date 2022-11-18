package com.movosoft.janari.Manager

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.movosoft.janari.Data.MerchantRequest
import com.movosoft.janari.Data.MerchantResponse
import com.movosoft.janari.LoginActivity
import com.movosoft.janari.Services.Api
import com.movosoft.janari.Services.RetrofitClient
import com.movosoft.janari.SignUpActivity
import com.movosoft.janari.databinding.ActivityCreateHotelBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateHotelActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateHotelBinding
    private val SharedPrefFiles = "sharedPrefData"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateHotelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnsubmit.setOnClickListener {

            val name =binding.edthotelname.text.toString().trim()

            if (TextUtils.isEmpty(name)){
                binding.edthotelname.error = "Enter hotel name"
                return@setOnClickListener
            }
             val  email = binding.edthotelemail.text.toString().trim()
            if(TextUtils.isEmpty(email)) {
                binding.edthotelemail.error = "Enter hotel email"
                return@setOnClickListener
            }
            val number = binding.number.text.toString().trim()
            if (TextUtils.isEmpty(number)) {
                binding.number.error = "Enter Phone number"
                return@setOnClickListener
            }
            val number1 = binding.edtnumber.text.toString().trim()
            if (TextUtils.isEmpty(number1)) {
                binding.edtnumber.error = "Enter mobile number"
                return@setOnClickListener
            }
            val id = binding.id.text.toString().trim()
            if (TextUtils.isEmpty(id)) {
                binding.id.error = "Enter operator ID"
                return@setOnClickListener
            }
            val branches = binding.edttotalbranches.text.toString().trim()
            if (TextUtils.isEmpty(branches)) {
                binding.edttotalbranches.error = "Enter total branches"
                return@setOnClickListener
            }
            val address = binding.edtaddress.text.toString().trim()
            if (TextUtils.isEmpty(address)) {
                binding.edtaddress.error = "Enter address"
                return@setOnClickListener
            }
            val add = binding.edtisadd.text.toString().trim()
            if (TextUtils.isEmpty(add)) {
                binding.edtisadd.error = "Enter is add"
                return@setOnClickListener
            }

           btnsumit(name,email, number, number1,id, branches,address,add)
        }


    }

    fun btnsumit(name:String, email:String, branches:String, phone:String, mobile:String, address:String, id:String, add:String){
        val sharedPreferences:SharedPreferences=getSharedPreferences(SharedPrefFiles, Context.MODE_PRIVATE)

        val progressDialog = ProgressDialog(this@CreateHotelActivity)
        progressDialog.setMessage("Loading...")
        progressDialog.setCancelable(false)
        progressDialog.show()

        val editor: SharedPreferences.Editor = sharedPreferences.edit()

        val retro = RetrofitClient.getRetroClientInstance().create(Api::class.java)
        val merchantRequest = MerchantRequest(MerchantName = name, Email = email, TotalBranches = branches, PhoneNumber=phone, MobileNo = mobile, Address = address, OperatorID = id, IsAdd = add)
        retro.MerchantSetup(merchantRequest).enqueue(object : Callback<MerchantResponse>{
            override fun onResponse(
                call: Call<MerchantResponse>,
                response: Response<MerchantResponse>
            ) {
                if (response.isSuccessful)
                    progressDialog.dismiss()

                val intent = Intent(this@CreateHotelActivity, SignUpActivity::class.java)
                startActivity(intent)

                Log.e("Done", "onSuccess: ${response.body()}")
                editor.putString("statuscode", response.body()!!.statusCode)
                editor.apply()

            }
            override fun onFailure(call: Call<MerchantResponse>, t: Throwable) {
           progressDialog.dismiss()
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
            }

        })
    }
}