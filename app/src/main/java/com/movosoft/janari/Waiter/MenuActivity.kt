package com.movosoft.janari.Waiter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.movosoft.janari.R
import com.movosoft.janari.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate( layoutInflater)
        setContentView(binding.root)

    }
}