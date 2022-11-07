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
import com.movosoft.janari.activities.cashier.CashierMenuActivity
import com.movosoft.janari.activities.chef.ChefMenuActivity
import com.movosoft.janari.activities.manager.ManagerMenuActivity
import com.movosoft.janari.activities.waiter.MenuActivity
import com.movosoft.janari.api.ApiClient
import com.movosoft.janari.api.SessionManager
import com.movosoft.janari.databinding.ActivityLoginBinding
import com.movosoft.janari.models.userModel.UserLogin
import com.movosoft.janari.models.userModel.UserLoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val sharedPrefFile = "sharedPrefData"
    private lateinit var sessionManager: SessionManager
    private lateinit var apiClient: ApiClient
//    private lateinit var settingsManager: SettingsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

//        settingsManager = SettingsManager(this)

        apiClient = ApiClient
        sessionManager = SessionManager(this)
        val sharedPreferences: SharedPreferences =
            getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)

        binding.login.setOnClickListener {
//            if(!binding.etEmail.text.toString().trim().matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex())) {
//                binding.etEmail.error = "Kindly enter a valid email!"
//            } else
            if (TextUtils.isEmpty(binding.etPin.text.toString().trim())) {
                binding.etPin.error = "Kindly enter pin"
            } else {
                val progressDialog = ProgressDialog(this@LoginActivity)
                progressDialog.setCancelable(false) // set cancelable to false
                progressDialog.setMessage("Logging in...") // set message
                progressDialog.show()

                val editor: SharedPreferences.Editor = sharedPreferences.edit()

                val loginInfo = UserLogin(
                    binding.etEmail.text.toString().trim(),
                    binding.etPin.text.toString().trim()
                )
                apiClient.getApiService(this).loginUser(loginInfo).enqueue(object :
                    Callback<UserLoginResponse> {
                    override fun onResponse(call: Call<UserLoginResponse>, response: Response<UserLoginResponse>) {
                        if (response.isSuccessful) {
                            progressDialog.dismiss()
                            Snackbar.make(it, "Login Successful", Snackbar.LENGTH_SHORT).show()
                            Log.e("Gideon", "onSuccess: ${response.body()}")
                            editor.putString("userID", response.body()!!.userID)
                            editor.putString("branchID", response.body()!!.branchID)
                            editor.putString("companyID", response.body()!!.companyID)
                            editor.putString("companyName", response.body()!!.companyName)
                            editor.putString("email", binding.etEmail.text.toString().trim())
                            editor.putString("firstName", response.body()!!.firstName)
                            editor.putString("role", response.body()!!.role)
                            editor.apply()

                            response.body()!!.tokenID?.let { it1 -> sessionManager.saveAuthToken(it1) }

                            when (response.body()!!.role) {
                                "CHF" -> {
                                    val intent = Intent(
                                        this@LoginActivity,
                                        ChefMenuActivity::class.java
                                    ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                    startActivity(intent)
                                }
                                "CSH" -> {
                                    val intent = Intent(
                                        this@LoginActivity,
                                        CashierMenuActivity::class.java
                                    ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                    startActivity(intent)
                                }
                                "WTR" -> {
                                    val intent =
                                        Intent(
                                            this@LoginActivity,
                                            MenuActivity::class.java
                                        ).addFlags(
                                            Intent.FLAG_ACTIVITY_CLEAR_TOP
                                        )
                                    startActivity(intent)
                                }
                                "MGR" -> {
                                    val intent = Intent(
                                        this@LoginActivity,
                                        ManagerMenuActivity::class.java
                                    ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                    startActivity(intent)
                                }
                                //TODO: to remove
                                "ADM" -> {
                                    val intent = Intent(
                                        this@LoginActivity,
                                        PinViewActivity::class.java
                                    ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                    startActivity(intent)
                                }
                            }

                        }
                    }

                    override fun onFailure(call: Call<UserLoginResponse>, t: Throwable) {
                        progressDialog.dismiss()
                        Snackbar.make(it, "${t.message}", Snackbar.LENGTH_SHORT).show()
                        Log.e("Gideon", "onFailure: ${t.message}")
                    }
                })
            }
        }

        binding.toSignup.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    SignUpActivity::class.java
                ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            )
        }
    }
}