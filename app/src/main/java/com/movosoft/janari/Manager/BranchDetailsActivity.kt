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
import com.movosoft.janari.databinding.ActivityBranchDetailsBinding

class BranchDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBranchDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBranchDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewreports.setOnClickListener {

        }
        binding.viewsales.setOnClickListener {

        }
        binding.viewstaff.setOnClickListener {

        }
        binding.bottomView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener{item ->
            when(item.itemId){
                R.id.nav_reports->{
                    startActivity(Intent(this@BranchDetailsActivity,ReportsActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_sales->{
                    startActivity(Intent(this@BranchDetailsActivity,SalesActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_menu->{
                    startActivity(Intent(this@BranchDetailsActivity,ManagerMenuActivity::class.java))
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
                R.id.nav_foodmenu->{
                    startActivity(Intent(this@BranchDetailsActivity, ManagerMenuActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_reports->{
                    startActivity(Intent(this@BranchDetailsActivity,ReportsActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_branch->{
                    startActivity(Intent(this@BranchDetailsActivity,BranchActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_aboutapp->{
                    startActivity(Intent(this@BranchDetailsActivity, AboutAppActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_hotelsettings->{
                    startActivity(Intent(this@BranchDetailsActivity,SettingsActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_logout->{
                    startActivity(Intent(this@BranchDetailsActivity,LoginActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_hotelstaff->{
                    startActivity(Intent(this@BranchDetailsActivity,StaffActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }

            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            Log.i(ContentValues.TAG, "onNavigationItemSelected:nothing clicked")
            false
        })
    }
}