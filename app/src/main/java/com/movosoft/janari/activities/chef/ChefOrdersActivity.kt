package com.movosoft.janari.activities.chef

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.movosoft.janari.R
import com.movosoft.janari.databinding.ActivityChefOrdersBinding

class ChefOrdersActivity : AppCompatActivity() {
    lateinit var binding: ActivityChefOrdersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChefOrdersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        binding.bottomNav.selectedItemId = R.id.newOrders

        binding.bottomNav.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.confirmed -> {
                    startActivity(Intent(applicationContext, ChefConfirmedActivity::class.java))
                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.menu -> {
                    startActivity(Intent(applicationContext, ChefMenuActivity::class.java))
                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.reports -> {
                    startActivity(Intent(applicationContext, ChefReportsActivity::class.java))
                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.newOrders -> return@OnNavigationItemSelectedListener true

            }
            false
        })

        binding.cardView4.setOnClickListener{
            val dialog = Dialog(this@ChefOrdersActivity)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(false)
            dialog.setContentView(R.layout.confirm_order_dialog)
            val close = dialog.findViewById(R.id.close) as ImageView
            val yesBtn = dialog.findViewById(R.id.confirm) as AppCompatButton
            val noBtn = dialog.findViewById(R.id.cancel) as AppCompatButton
            yesBtn.setOnClickListener {
                dialog.dismiss()
            }
            noBtn.setOnClickListener { dialog.dismiss() }
            dialog.show()
        }
    }
}