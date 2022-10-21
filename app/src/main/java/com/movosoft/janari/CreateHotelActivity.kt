package com.movosoft.janari

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.movosoft.janari.databinding.ActivityCreateHotelBinding

class CreateHotelActivity : AppCompatActivity() {
    lateinit var binding: ActivityCreateHotelBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateHotelBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }
    }
}