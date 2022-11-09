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
import com.movosoft.janari.databinding.ActivityFoodDetailsBinding

class FoodDetailsActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityFoodDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoodDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.nav_reports->{
                    startActivity(Intent(this@FoodDetailsActivity,ReportsActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_menu->{
                    startActivity(Intent(this@FoodDetailsActivity,ManagerMenuActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_sales->{
                    startActivity(Intent(this@FoodDetailsActivity,SalesActivity::class.java))
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
            when(item.itemId){
                R.id.nav_reports->{
                    startActivity(Intent(this@FoodDetailsActivity, ReportsActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_hotelstaff->{
                    startActivity(Intent(this@FoodDetailsActivity,StaffActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_logout->{
                    startActivity(Intent(this@FoodDetailsActivity,LoginActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_foodmenu->{
                    startActivity(Intent(this@FoodDetailsActivity,ManagerMenuActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_hotelsettings->{
                    startActivity(Intent(this@FoodDetailsActivity,SettingsActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_aboutapp->{
                    startActivity(Intent(this@FoodDetailsActivity,AboutAppActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_branch->{
                    startActivity(Intent(this@FoodDetailsActivity, BranchActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            Log.i(ContentValues.TAG, "onNavigationItemSelected:nothingclicked")
            false
        })
    }
}