package com.movosoft.janari.Chef

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.movosoft.janari.All.ReportsActivity
import com.movosoft.janari.R
import com.movosoft.janari.Waiter.CartActivity
import com.movosoft.janari.databinding.ActivityChefMenuBinding

class ChefMenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChefMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChefMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.nav_reports -> {
                    startActivity(Intent(this@ChefMenuActivity, ReportsActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_foodmenu -> {
                    startActivity(Intent(this@ChefMenuActivity, ChefMenuActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_cart -> {
                    startActivity(Intent(this@ChefMenuActivity, CartActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        })
    }
}