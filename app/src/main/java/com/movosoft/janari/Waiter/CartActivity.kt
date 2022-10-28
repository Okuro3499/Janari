package com.movosoft.janari.Waiter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.movosoft.janari.R
import com.movosoft.janari.All.ReportsActivity
import com.movosoft.janari.databinding.ActivityCartBinding

class CartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.nav_foodmenu -> {
                    startActivity(Intent(this@CartActivity, FoodMenuActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_reports -> {
                    startActivity(Intent(this@CartActivity, ReportsActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_cart -> {
                    startActivity(Intent(this@CartActivity, CartActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        })
    }
}