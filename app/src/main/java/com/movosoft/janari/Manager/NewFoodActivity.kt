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
import com.movosoft.janari.databinding.ActivityNewFoodBinding

class NewFoodActivity : AppCompatActivity() {
    private lateinit var binding:ActivityNewFoodBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnsubmit.setOnClickListener {
            startActivity(Intent(this@NewFoodActivity, ManagerMenuActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))

        }
        binding.bottomView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener{item ->
            when(item.itemId){
                R.id.nav_menu ->{
                    startActivity(Intent(this@NewFoodActivity, ManagerMenuActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_sales->{
                    startActivity(Intent(this@NewFoodActivity,SalesActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_reports ->{
                    startActivity(Intent(this@NewFoodActivity,ReportsActivity::class.java))
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
                R.id.nav_reports ->{
                    startActivity(Intent(this@NewFoodActivity, ReportsActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_foodmenu ->{
                    startActivity(Intent(this@NewFoodActivity, ManagerMenuActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_hotelstaff->{
                    startActivity(Intent(this@NewFoodActivity, StaffActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_logout->{
                    startActivity(Intent(this@NewFoodActivity, LoginActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_hotelsettings->{
                    startActivity(Intent(this@NewFoodActivity, SettingsActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_aboutapp->{
                    startActivity(Intent(this@NewFoodActivity, AboutAppActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_branch->{
                    startActivity(Intent(this@NewFoodActivity,BranchActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }

            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            Log.i(ContentValues.TAG, "onNavigationItemSelected: nothing clicked")
            false
        })
    }
}