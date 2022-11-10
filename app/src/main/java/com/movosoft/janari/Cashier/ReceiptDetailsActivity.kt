package com.movosoft.janari.Cashier

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.movosoft.janari.All.ReportsActivity
import com.movosoft.janari.R
import com.movosoft.janari.databinding.ActivityReceiptDetailsBinding

class ReceiptDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReceiptDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReceiptDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.viewprint.setOnClickListener {  }

        binding.bottomView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener{item ->
            when(item.itemId){
                R.id.nav_reports->{
                    startActivity(Intent(this@ReceiptDetailsActivity, ReportsActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_menu->{
                    startActivity(Intent(this@ReceiptDetailsActivity,MenuActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_sales->{
                    startActivity(Intent(this@ReceiptDetailsActivity, CashierSalesActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }

            }
            false
        })
    }
}