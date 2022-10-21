package com.movosoft.janari.manager

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import com.movosoft.janari.R
import com.movosoft.janari.databinding.ActivityBranchBinding

class BranchActivity : AppCompatActivity() {
    lateinit var binding: ActivityBranchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBranchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        binding.nav.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }

        binding.navView.setNavigationItemSelectedListener(NavigationView.OnNavigationItemSelectedListener { item ->
            Log.i(ContentValues.TAG, "onNavigationItemSelected: " + item.itemId)
            //TODO: set visibility
            when (item.itemId) {
                R.id.branches -> {
                    startActivity(Intent(this@BranchActivity, BranchActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.foodMenu -> {
                    startActivity(Intent(this@BranchActivity, ManagerMenuActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.staff -> {
                    startActivity(Intent(this@BranchActivity, StaffActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.reports -> {
                    startActivity(Intent(this@BranchActivity, ManagerReportsActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.settings -> {
                    //TODO: create hotel settings activity
                    startActivity(Intent(this@BranchActivity, BranchActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.about -> {
                    //TODO: create about activity
                    startActivity(Intent(this@BranchActivity, BranchActivity::class.java))
                    return@OnNavigationItemSelectedListener true

                }
                R.id.logout -> {

                }
            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            Log.i(ContentValues.TAG, "onNavigationItemSelected: nothing clicked")
            false
        })
    }
}