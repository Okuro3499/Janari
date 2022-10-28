package com.movosoft.janari.Manager

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.GravityCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.movosoft.janari.*
import com.movosoft.janari.All.ReportsActivity
import com.movosoft.janari.Waiter.FoodMenuActivity
import com.movosoft.janari.databinding.ActivitySales2Binding

class SalesActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySales2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySales2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener{item ->
            when (item.itemId){
                R.id.nav_sales -> {
                    startActivity(Intent(this@SalesActivity, SalesActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_foodmenu -> {
                    startActivity(Intent(this@SalesActivity, FoodMenuActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_reports -> {
                    startActivity(Intent(this@SalesActivity, ReportsActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }

            }
            false
        })
        binding.nav.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
        binding.navView.setNavigationItemSelectedListener(NavigationView.OnNavigationItemSelectedListener { item ->
            Log.i(ContentValues.TAG, "onNavigationItemSelected:" + item.itemId)
            when (item.itemId) {
                R.id.nav_hotelstaff -> {
                    startActivity(Intent(this@SalesActivity, StaffActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_reports -> {
                    startActivity(Intent(this@SalesActivity, ReportsActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_foodmenu -> {
                    startActivity(Intent(this@SalesActivity, FoodMenuActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_branch -> {
                    startActivity(Intent(this@SalesActivity, BranchActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_hotelsettings -> {
                    startActivity(Intent(this@SalesActivity, SettingsActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_logout -> {
                    startActivity(Intent(this@SalesActivity, LoginActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_aboutapp -> {
                    startActivity(Intent(this@SalesActivity, AboutAppActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            Log.i(ContentValues.TAG, "onNavigationItemSelected:" +item.itemId)
            false
        })
    }
}
