package com.movosoft.janari.Manager

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.GravityCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.movosoft.janari.All.ReportsActivity
import com.movosoft.janari.LoginActivity
import com.movosoft.janari.R
import com.movosoft.janari.databinding.ActivityNewBranchBinding

class NewBranchActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewBranchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewBranchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if(actionBar?.isShowing ==true){
            actionBar!!.hide()
        }
        binding.bottomView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.nav_foodmenu ->{
                    startActivity(Intent(this@NewBranchActivity, ManagerMenuActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_sales->{
                    startActivity(Intent(this@NewBranchActivity,SalesActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_reports->{
                    startActivity(Intent(this@NewBranchActivity,ReportsActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        })
        binding.btnsubmit.setOnClickListener {

        }
        binding.nav.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
        binding.navView.setNavigationItemSelectedListener(NavigationView.OnNavigationItemSelectedListener { item ->
            Log.i(ContentValues.TAG, "onNavigationItemSelected:" +item.itemId)
            when (item.itemId){
                R.id.nav_branch ->{
                    startActivity(Intent(this@NewBranchActivity, BranchActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_foodmenu ->{
                    startActivity(Intent(this@NewBranchActivity, ManagerMenuActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_reports->{
                    startActivity(Intent(this@NewBranchActivity,ReportsActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_aboutapp ->{
                    startActivity(Intent(this@NewBranchActivity,AboutAppActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_logout ->{
                    startActivity(Intent(this@NewBranchActivity, LoginActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_hotelstaff -> {
                    startActivity(Intent(this@NewBranchActivity, StaffActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_hotelsettings -> {
                    startActivity(Intent(this@NewBranchActivity, SettingsActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }

            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            Log.i(ContentValues.TAG, "onNavigationItemSelected: nothing clicked")
            false
        })
    }
}