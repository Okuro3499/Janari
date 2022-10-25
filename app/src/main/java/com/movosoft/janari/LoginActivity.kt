package com.movosoft.janari

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.movosoft.janari.databinding.ActivityLoginBinding
import com.movosoft.janari.databinding.ActivitySignUpBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.login.setOnClickListener{
            startActivity(
                Intent(
                    this,
                    NewPinActivity::class.java
                ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            )
        }

        binding.toSignup.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    PinViewActivity::class.java
                ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            )
        }
    }
}