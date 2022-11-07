package com.movosoft.janari.activities.cashier

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.movosoft.janari.databinding.ActivityCashierSaleDetailBinding

class CashierSaleDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityCashierSaleDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCashierSaleDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }
    }
}