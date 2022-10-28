package com.movosoft.janari.Manager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.movosoft.janari.R
import com.movosoft.janari.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
    }
}