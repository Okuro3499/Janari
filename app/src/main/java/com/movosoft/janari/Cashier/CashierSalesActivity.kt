package com.movosoft.janari.Cashier

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.movosoft.janari.All.ReportsActivity
import com.movosoft.janari.R
import com.movosoft.janari.databinding.ActivityCashierSalesBinding

class CashierSalesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCashierSalesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCashierSalesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId){
                R.id.nav_sales->{
                    startActivity(Intent(this@CashierSalesActivity, CashierSalesActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_foodmenu->{
                    startActivity(Intent(this@CashierSalesActivity, MenuActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_reports->{
                    startActivity(Intent(this@CashierSalesActivity,ReportsActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        })
    }
}