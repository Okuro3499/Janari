package com.movosoft.janari.activities.cashier

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.movosoft.janari.R
import com.movosoft.janari.databinding.ActivityCashierSalesBinding

class CashierSalesActivity : AppCompatActivity() {
    lateinit var binding: ActivityCashierSalesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCashierSalesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        binding.bottomNav.selectedItemId = R.id.sales

        binding.bottomNav.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu -> {
                    startActivity(Intent(applicationContext, CashierMenuActivity::class.java))
                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.reports -> {
                    startActivity(Intent(applicationContext, CashierReportsActivity::class.java))
                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.sales -> return@OnNavigationItemSelectedListener true

            }
            false
        })

        binding.cardView4.setOnClickListener {
            startActivity(Intent(applicationContext, CashierSaleDetailActivity::class.java))
        }
    }
}