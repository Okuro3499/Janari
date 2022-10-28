package com.movosoft.janari.Cashier

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.movosoft.janari.All.ReportsActivity
import com.movosoft.janari.Manager.SalesActivity
import com.movosoft.janari.R
import com.movosoft.janari.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.nav_foodmenu->{
                    startActivity(Intent(this@MenuActivity,MenuActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_sales->{
                    startActivity(Intent(this@MenuActivity, CashierSalesActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_reports->{
                    startActivity(Intent(this@MenuActivity,ReportsActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        })
    }
}