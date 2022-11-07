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
import com.movosoft.janari.api.ApiClient
import com.movosoft.janari.api.SessionManager
import com.movosoft.janari.databinding.ActivitySignUpBinding
import com.movosoft.janari.models.userModel.CreateUserModel
import com.movosoft.janari.models.userModel.CreateUserResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private val sharedPrefFile = "sharedPrefData"
    private lateinit var sessionManager: SessionManager
    private lateinit var apiClient: ApiClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        apiClient = ApiClient
        sessionManager = SessionManager(this)
        val sharedPreferences: SharedPreferences =
            getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)

        binding.submit.setOnClickListener {
            if (TextUtils.isEmpty(binding.etFirstName.text.toString().trim())) {
                binding.etFirstName.error = "Kindly enter first name";
            } else if (TextUtils.isEmpty(binding.etLastName.text.toString().trim())) {
                binding.etLastName.error = "Kindly enter last name";
            } else if (TextUtils.isEmpty(binding.etUsername.text.toString().trim())) {
                binding.etUsername.error = "Kindly enter username";
            } else if (TextUtils.isEmpty(binding.etEmail.text.toString().trim())) {
                binding.etEmail.error = "Kindly enter your email";
            } else if (TextUtils.isEmpty(binding.etBranchName.text.toString().trim())) {
                binding.etBranchName.error = "Kindly enter branch name";
            } else if (TextUtils.isEmpty(binding.etAddress.text.toString().trim())) {
                binding.etAddress.error = "Kindly enter address";
            } else if (TextUtils.isEmpty(binding.etPin.text.toString().trim())) {
                binding.etPin.error = "Kindly enter Pin";
            } else if (binding.etConfirmPin.text.toString().trim() != binding.etPin.text.toString()
                    .trim()
            ) {
                binding.etConfirmPin.error = "Pins do not match!";
            } else {
                val progressDialog = ProgressDialog(this)
                progressDialog.setCancelable(false) // set cancelable to false
                progressDialog.setMessage("Creating User...") // set message
                progressDialog.show()

                val editor: SharedPreferences.Editor = sharedPreferences.edit()

                val createUserModel = CreateUserModel(
                    binding.etFirstName.text.toString().trim(),
                    binding.etLastName.text.toString().trim(),
                    binding.etUsername.text.toString().trim(),
                    binding.etAddress.text.toString().trim(),
                    "Admin",
                    binding.etBranchName.text.toString().trim(),
                    binding.etEmail.text.toString().trim(),
                    binding.etPin.text.toString().trim(),
                    Integer.valueOf(sharedPreferences.getString("MerchantId", "").toString()),
                    "info@movosoft.co.ke",
                    1
                )
                apiClient.getApiService(this).createUser(createUserModel).enqueue(object : Callback<CreateUserResponseModel> { override fun onResponse(call: Call<CreateUserResponseModel>, response: Response<CreateUserResponseModel>) {
                        if (response.isSuccessful) {
                            Log.e("Gideon", "onSuccess: ${response.body()}")
                            progressDialog.dismiss()
                            Snackbar.make(it, "User created Successfully", Snackbar.LENGTH_SHORT)
                                .show()
                            val intent =
                                Intent(this@SignUpActivity, LoginActivity::class.java).addFlags(
                                    Intent.FLAG_ACTIVITY_CLEAR_TOP
                                )
                            startActivity(intent)
                        }
                    }

                    override fun onFailure(call: Call<CreateUserResponseModel>, t: Throwable) {
                        progressDialog.dismiss()
                        Snackbar.make(it, "${t.message}", Snackbar.LENGTH_SHORT).show()
                        Log.e("Gideon", "onFailure: ${t.message}")
                    }
                })
            }
        }

        binding.toLogin.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    LoginActivity::class.java
                ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            )
        }
    }
}