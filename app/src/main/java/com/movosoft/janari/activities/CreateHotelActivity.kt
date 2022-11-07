package com.movosoft.janari.activities

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.movosoft.janari.activities.chef.ChefMenuActivity
import com.movosoft.janari.api.ApiClient
import com.movosoft.janari.api.SessionManager
import com.movosoft.janari.databinding.ActivityCreateHotelBinding
import com.movosoft.janari.models.hotelModel.HotelSetUp
import com.movosoft.janari.models.hotelModel.HotelSetUpResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateHotelActivity : AppCompatActivity() {
    lateinit var binding: ActivityCreateHotelBinding
    private val sharedPrefFile = "sharedPrefData"
    private lateinit var sessionManager: SessionManager
    private lateinit var apiClient: ApiClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateHotelBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        apiClient = ApiClient
        sessionManager = SessionManager(this)
        val sharedPreferences: SharedPreferences = getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)

        binding.submit.setOnClickListener {
            if (TextUtils.isEmpty(binding.etHotelName.text.toString().trim())) {
                binding.etHotelName.error = "Kindly enter hotel name";
            } else if (TextUtils.isEmpty(binding.etHotelEmail.text.toString().trim())) {
                binding.etHotelEmail.error ="Kindly enter hotel email";
            } else if (TextUtils.isEmpty(binding.etHotelPhoneNumber.text.toString().trim())) {
                binding.etHotelPhoneNumber.error ="Kindly enter hotel phone number";
            } else if (TextUtils.isEmpty(binding.etAddress.text.toString().trim())) {
                binding.etAddress.error ="Kindly enter hotel address";
            }  else if (TextUtils.isEmpty(binding.etTotalBranches.text.toString().trim())) {
                binding.etTotalBranches.error ="Kindly enter no. of branches";
            }
            else {
                val progressDialog = ProgressDialog(this)
                progressDialog.setCancelable(false) // set cancelable to false
                progressDialog.setMessage("Creating Hotel...") // set message
                progressDialog.show()

                val editor: SharedPreferences.Editor = sharedPreferences.edit()

                val hotelSetup = HotelSetUp(
                    binding.etHotelName.text.toString().trim(),
                    binding.etHotelEmail.text.toString().trim(),
                    Integer.valueOf(binding.etTotalBranches.text.toString().trim()),
                    binding.etHotelPhoneNumber.text.toString().trim(),
                    binding.etHotelPhoneNumber.text.toString().trim(),
                    binding.etAddress.text.toString().trim(),
                    "info@movosoft.co.ke",
                    1
                )
                apiClient.getApiService(this).createHotel(hotelSetup).enqueue(object : Callback<HotelSetUpResponse> {
                        override fun onResponse(call: Call<HotelSetUpResponse>, response: Response<HotelSetUpResponse>) {
                            if (response.isSuccessful) {
                                Log.e("Gideon", "onSuccess: ${response.body()}")
                                progressDialog.dismiss()
                                editor.putString("MerchantId", response.body()!!.id)
                                editor.apply()
                                Snackbar.make(it, "Hotel created Successfully", Snackbar.LENGTH_SHORT).show()
                                val intent = Intent(this@CreateHotelActivity, SignUpActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                startActivity(intent)
                            }
                        }

                        override fun onFailure(call: Call<HotelSetUpResponse>, t: Throwable) {
                            progressDialog.dismiss()
                            Snackbar.make(it, "${t.message}", Snackbar.LENGTH_SHORT).show()
                            Log.e("Gideon", "onFailure: ${t.message}")
                        }
                    })
            }
        }
    }
}