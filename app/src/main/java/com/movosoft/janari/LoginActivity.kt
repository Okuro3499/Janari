package com.movosoft.janari

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.movosoft.janari.Chef.ChefMenuActivity
import com.movosoft.janari.Data.UserRequest
import com.movosoft.janari.Data.UserResponse
import com.movosoft.janari.Services.Api
import com.movosoft.janari.Services.RetrofitClient
import com.movosoft.janari.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val sharedPrefFile = "sharedPrefData"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)




        binding.login.setOnClickListener {

            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPin.text.toString().trim()


            if (email.isEmpty()) {
                binding.etEmail.error = "Email required"
            }

            else if (password.isEmpty()) {
                binding.etPin.error = "Password required"
            }
            else{
                login( email, password)
            }
        }
    }

    fun login(email: String, password: String) {

        val sharedPreferences: SharedPreferences =
            getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)



        val progressDialog = ProgressDialog(this@LoginActivity)
        progressDialog.setMessage("Loading...")
        progressDialog.setCancelable(false)
        progressDialog.show()

        val editor: SharedPreferences.Editor = sharedPreferences.edit()


        val retro = RetrofitClient.getRetroClientInstance().create(Api::class.java)
        val userRequest = UserRequest(Email=email, password = password)
        retro.Login(userRequest).enqueue(object : Callback<UserResponse> {
            override fun onResponse(
                call: Call<UserResponse>,
                response: Response<UserResponse>
            ) {

                if (response.isSuccessful) {
                    progressDialog.dismiss()

                    Log.e("Done", "onSuccess: ${response.body()}")
                    editor.putString("tokenID", response.body()!!.tokenID)
                    editor.apply()

                    val intent =Intent(this@LoginActivity, ChefMenuActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(intent)
                }
                else{
                       Toast.makeText(applicationContext, "${response.body()?.message}",Toast.LENGTH_LONG).show()
                    }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                progressDialog.dismiss()
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
            }

        })

    }
}

