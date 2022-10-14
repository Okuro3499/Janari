package com.movosoft.janari

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.movosoft.janari.databinding.ActivityNewPinBinding

class NewPinActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewPinBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewPinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.submit.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    PinViewActivity::class.java
                ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            )
        }
    }
}