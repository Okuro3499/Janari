package com.movosoft.janari.Chef

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.movosoft.janari.All.ReportsActivity
import com.movosoft.janari.R
import com.movosoft.janari.Waiter.FoodMenuActivity
import com.movosoft.janari.databinding.ActivityOrdersBinding

class OrdersActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOrdersBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrdersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.nav_reports ->{
                    startActivity(Intent(this@OrdersActivity,ReportsActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_foodmenu -> {
                    startActivity(Intent(this@OrdersActivity, ChefMenuActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_neworders ->{
                    startActivity(Intent(this@OrdersActivity,OrdersActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_confirmed -> {
                    startActivity(Intent(this@OrdersActivity, ConfirmedActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }

            }
            false
        })
    }
}