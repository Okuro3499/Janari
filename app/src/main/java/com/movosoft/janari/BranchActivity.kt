package com.movosoft.janari

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import com.movosoft.janari.databinding.ActivityBranchBinding

class BranchActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBranchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBranchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.nav.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
        binding.navView.setNavigationItemSelectedListener(NavigationView.OnNavigationItemSelectedListener { item ->
            Log.i(ContentValues.TAG, "onNavigationItemSelected:" + item.itemId)
            when (item.itemId){
                R.id.nav_branch -> {
                    startActivity(Intent(this@BranchActivity, BranchActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_foodmenu -> {
                    startActivity(Intent(this@BranchActivity, FoodMenuActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_hotelsettings -> {
                    startActivity(Intent(this@BranchActivity, SettingsActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_reports -> {
                    startActivity(Intent(this@BranchActivity, ReportsActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_aboutapp -> {
                    startActivity(Intent(this@BranchActivity, AboutAppActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_logout -> {
                    startActivity(Intent(this@BranchActivity, LoginActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_hotelstaff -> {
                    startActivity(Intent(this@BranchActivity, StaffActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            Log.i(ContentValues.TAG, "onNavigationItemselected:nothing Clicked")
            false
        })

    }
}