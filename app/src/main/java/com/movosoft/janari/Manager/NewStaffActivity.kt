package com.movosoft.janari.Manager

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import com.movosoft.janari.R
import com.movosoft.janari.databinding.ActivityNewStaffBinding

class NewStaffActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewStaffBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewStaffBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnsubmit.setOnClickListener {
            startActivity(Intent(this@NewStaffActivity,StaffActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
        }


        binding.nav.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
        binding.navView.setNavigationItemSelectedListener(NavigationView.OnNavigationItemSelectedListener { item ->
            Log.i(ContentValues.TAG, "onNavigationItemSelected:"+ item.itemId)
            when(item.itemId){

            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            Log.i(ContentValues.TAG, "onNavigationItemSeleected:nothing clicked")
            false
        })


    }
}