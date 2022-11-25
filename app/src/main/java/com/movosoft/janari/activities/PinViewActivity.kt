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
import com.movosoft.janari.databinding.ActivityPinViewBinding
import com.movosoft.janari.models.userModel.UserLogin
import com.movosoft.janari.models.userModel.UserLoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.String.format


class PinViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPinViewBinding
    private val sharedPrefFile = "sharedPrefData"
    private lateinit var sessionManager: SessionManager
    private lateinit var apiClient: ApiClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPinViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        apiClient = ApiClient
        sessionManager = SessionManager(this)
        val sharedPreferences: SharedPreferences = getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)

        binding.bt0.setOnClickListener {
            binding.edtPassword.text = format("%s0", binding.edtPassword.text.toString())
        }

        binding.bt1.setOnClickListener {
            binding.edtPassword.text = format(
                "%s1",
                binding.edtPassword.text.toString()
            )
        }

        binding.bt2.setOnClickListener {
            binding.edtPassword.text = format(
                "%s2",
                binding.edtPassword.text.toString()
            )
        }

        binding.bt3.setOnClickListener {
            binding.edtPassword.text = format(
                "%s3",
                binding.edtPassword.text.toString()
            )
        }

        binding.bt4.setOnClickListener {
            binding.edtPassword.text = format(
                "%s4",
                binding.edtPassword.text.toString()
            )
        }

        binding.bt5.setOnClickListener {
            binding.edtPassword.text = format(
                "%s5",
                binding.edtPassword.text.toString()
            )
        }

        binding.bt6.setOnClickListener {
            binding.edtPassword.text = format(
                "%s6",
                binding.edtPassword.text.toString()
            )
        }

        binding.bt8.setOnClickListener {
            binding.edtPassword.text = format(
                "%s7",
                binding.edtPassword.text.toString()
            )
        }

        binding.bt8.setOnClickListener {
            binding.edtPassword.text = format(
                "%s8",
                binding.edtPassword.text.toString()
            )
        }

        binding.bt9.setOnClickListener {
            binding.edtPassword.text = format(
                "%s9",
                binding.edtPassword.text.toString()
            )
        }

        binding.btCancel.setOnClickListener {
            var str: String = binding.edtPassword.text.toString()
            if (str.length > 1) {
                str = str.substring(0, str.length - 1)
                binding.edtPassword.text = str
            } else {
                str.length
                binding.edtPassword.text = ""
            }
        }

        binding.btOk.setOnClickListener {
            if (TextUtils.isEmpty(binding.edtPassword.text.toString().trim())) {
                binding.err.text = "Enter your PIN!"
            } else if (binding.edtPassword.text.toString().trim().length < 4) {
                binding.err.text = "PIN must be 4 digits!"
            }
            //TODO: remove
//            else if (binding.edtPassword.text.toString() == "0000") {
//                startActivity(
//                    Intent(
//                        this,
//                        ManagerMenuActivity::class.java
//                    ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//                )
//            }
            else {
                val progressDialog = ProgressDialog(this@PinViewActivity)
                progressDialog.setCancelable(false) // set cancelable to false
                progressDialog.setMessage("Logging in...") // set message
                progressDialog.show()

                val editor: SharedPreferences.Editor = sharedPreferences.edit()

                val loginInfo = UserLogin(
                    sharedPreferences.getString("email", ""), binding.edtPassword.text.toString()
                )
                apiClient.getApiService(this).loginUser(loginInfo).enqueue(object :
                    Callback<UserLoginResponse> {
                    override fun onResponse(
                        call: Call<UserLoginResponse>,
                        response: Response<UserLoginResponse>
                    ) {
                        if (response.isSuccessful) {
                            progressDialog.dismiss()
                            Snackbar.make(it, "Login Successful", Snackbar.LENGTH_SHORT).show()
                            Log.e("Gideon", "onSuccess: ${response.body()}")
                            editor.putString("userID", response.body()!!.userID)
                            editor.putString("branchID", response.body()!!.branchID)
                            editor.putString("companyID", response.body()!!.companyID)
                            editor.putString("companyName", response.body()!!.companyName)
                            editor.putString("firstName", response.body()!!.firstName)
                            editor.putString("role", response.body()!!.role)
                            editor.apply()

                            response.body()!!.tokenID?.let { it1 -> sessionManager.saveAuthToken(it1) }

                            when (response.body()!!.role) {
                                "CHF" -> {
                                    val intent = Intent(
                                        this@PinViewActivity,
                                        ChefMenuActivity::class.java
                                    ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                    startActivity(intent)
                                }
                                "CSH" -> {
                                    val intent = Intent(
                                        this@PinViewActivity,
                                        CashierMenuActivity::class.java
                                    ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                    startActivity(intent)
                                }
                                "WTR" -> {
                                    val intent =
                                        Intent(
                                            this@PinViewActivity,
                                            MenuActivity::class.java
                                        ).addFlags(
                                            Intent.FLAG_ACTIVITY_CLEAR_TOP
                                        )
                                    startActivity(intent)
                                }
                                "MGR" -> {
                                    val intent = Intent(
                                        this@PinViewActivity,
                                        ManagerMenuActivity::class.java
                                    ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                    startActivity(intent)
                                }
                                //TODO: to remove
                                "ADM" -> {
                                    val intent = Intent(
                                        this@PinViewActivity,
                                        ManagerMenuActivity::class.java
                                    ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                    startActivity(intent)
                                }
                            }

                        }
                    }

                    override fun onFailure(call: Call<UserLoginResponse>, t: Throwable) {
                        progressDialog.dismiss()
                        Snackbar.make(it, "${t.message}", Snackbar.LENGTH_SHORT).show()
                        binding.err.text = "${t.message}"
                        Log.e("Gideon", "onFailure: ${t.message}")
                    }
                })
            }
        }
        binding.forgotPin.setOnClickListener {

            startActivity(
                Intent(
                    this,
                    CashierMenuActivity::class.java
                ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            )
        }
    }

}