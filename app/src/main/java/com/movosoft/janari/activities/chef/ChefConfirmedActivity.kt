package com.movosoft.janari.activities.chef

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.movosoft.janari.R
import com.movosoft.janari.databinding.ActivityChefConfirmedBinding

class ChefConfirmedActivity : AppCompatActivity() {
    lateinit var binding: ActivityChefConfirmedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChefConfirmedBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        binding.bottomNav.selectedItemId = R.id.confirmed

        binding.bottomNav.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.confirmed -> return@OnNavigationItemSelectedListener true
                R.id.menu -> {
                    startActivity(Intent(applicationContext, ChefMenuActivity::class.java))
                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.reports -> {
                    startActivity(Intent(applicationContext, ChefReportsActivity::class.java))
                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.newOrders -> {
                    startActivity(Intent(applicationContext, ChefOrdersActivity::class.java))
                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        })
    }
}