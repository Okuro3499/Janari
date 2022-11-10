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
import com.movosoft.janari.databinding.ActivityNewStaffBinding

class NewStaffActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewStaffBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewStaffBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.nav_reports->{
                    startActivity(Intent(this@NewStaffActivity,ReportsActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_sales->{
                    startActivity(Intent(this@NewStaffActivity,SalesActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_menu->{
                    startActivity(Intent(this@NewStaffActivity,ManagerMenuActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }

            }
            false
        })
        binding.btnsubmit.setOnClickListener {
            startActivity(Intent(this@NewStaffActivity,StaffActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
        }


        binding.nav.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
        binding.navView.setNavigationItemSelectedListener(NavigationView.OnNavigationItemSelectedListener { item ->
            Log.i(ContentValues.TAG, "onNavigationItemSelected:"+ item.itemId)
            when(item.itemId){
                R.id.nav_branch->{
                    startActivity(Intent(this@NewStaffActivity,BranchActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_foodmenu->{
                    startActivity(Intent(this@NewStaffActivity, ManagerMenuActivity::class.java ))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_hotelstaff->{
                    startActivity(Intent(this@NewStaffActivity, StaffActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_reports->{
                    startActivity(Intent(this@NewStaffActivity,ReportsActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_hotelsettings->{
                    startActivity(Intent(this@NewStaffActivity,SettingsActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_aboutapp->{
                    startActivity(Intent(this@NewStaffActivity,AboutAppActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_logout->{
                    startActivity(Intent(this@NewStaffActivity,LoginActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }

            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            Log.i(ContentValues.TAG, "onNavigationItemSeleected:nothing clicked")
            false
        })


    }
}