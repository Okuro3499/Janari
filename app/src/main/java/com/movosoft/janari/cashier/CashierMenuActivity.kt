package com.movosoft.janari.cashier

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.movosoft.janari.R
import com.movosoft.janari.databinding.ActivityCashierMenuBinding
import com.movosoft.janari.databinding.ActivityMenuBinding
import com.movosoft.janari.waiter.CartActivity
import com.movosoft.janari.waiter.ReportsActivity

class CashierMenuActivity : AppCompatActivity() {
    lateinit var binding: ActivityCashierMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCashierMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        binding.bottomNav.selectedItemId = R.id.menu

        binding.bottomNav.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu -> return@OnNavigationItemSelectedListener true
                R.id.reports -> {
                    startActivity(Intent(applicationContext, CashierReportsActivity::class.java))
                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.sales -> {
                    startActivity(Intent(applicationContext, CashierSalesActivity::class.java))
                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        })
    }
}