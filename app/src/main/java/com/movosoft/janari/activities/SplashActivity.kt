package com.movosoft.janari.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.movosoft.janari.R
import com.movosoft.janari.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    private val sharedPrefFile = "sharedPrefData"
    private val SPLASH_SCREEN = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }


        Glide.with(this).asGif().load(R.drawable.loader).error(R.drawable.error_img)
            .into(binding.loader)


    }

    override fun onStart() {
        super.onStart()
        val sharedPreferences: SharedPreferences =
            getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        Handler().postDelayed({
            if (sharedPreferences.getString("email", "") == "") {
                startActivity(
                    Intent(
                        this,
                        LoginActivity::class.java
                    ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                )
            } else {
                startActivity(
                    Intent(
                        this,
                        PinViewActivity::class.java
                    ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                )
            }
        }, SPLASH_SCREEN.toLong())
    }
}