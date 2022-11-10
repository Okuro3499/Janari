package com.movosoft.janari.Chef

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.movosoft.janari.All.ReportsActivity
import com.movosoft.janari.R
import com.movosoft.janari.databinding.ActivityConfirmedBinding

class ConfirmedActivity : AppCompatActivity() {
    private lateinit var binding: ActivityConfirmedBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfirmedBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.nav_confirmed->{
                    startActivity(Intent(this@ConfirmedActivity, ConfirmedActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_menu ->{
                    startActivity(Intent(this@ConfirmedActivity, ChefMenuActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_reports -> {
                    startActivity(Intent(this@ConfirmedActivity,ReportsActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_neworders->{
                    startActivity(Intent(this@ConfirmedActivity, OrdersActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        })
    }
}