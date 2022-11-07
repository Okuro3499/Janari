package com.movosoft.janari.activities.manager

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.movosoft.janari.R
import com.movosoft.janari.databinding.ActivityManagerReportsBinding
import com.movosoft.janari.activities.manager.adapters.ManagerFragmentAdapter

class ManagerReportsActivity : AppCompatActivity() {
    private var adapter: ManagerFragmentAdapter? = null
    lateinit var binding: ActivityManagerReportsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityManagerReportsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Graphs"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Lists"))

        val fragmentManager = supportFragmentManager
        adapter = ManagerFragmentAdapter(fragmentManager, lifecycle)
        binding.viewPager2.adapter = adapter

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                binding.viewPager2.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        binding.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position))
            }
        })

        binding.nav.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }

        binding.navView.setNavigationItemSelectedListener(NavigationView.OnNavigationItemSelectedListener { item ->
            Log.i(ContentValues.TAG, "onNavigationItemSelected: " + item.itemId)
            //TODO: set visibility
            when (item.itemId) {
                R.id.branches -> {
                    startActivity(Intent(this@ManagerReportsActivity, BranchActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.foodMenu -> {
                    startActivity(Intent(this@ManagerReportsActivity, ManagerMenuActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.staff -> {
                    startActivity(Intent(this@ManagerReportsActivity, StaffActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.reports -> {
                    startActivity(Intent(this@ManagerReportsActivity, ManagerReportsActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.settings -> {
                    //TODO: create hotel settings activity
                    startActivity(Intent(this@ManagerReportsActivity, BranchActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.about -> {
                    //TODO: create about activity
                    startActivity(Intent(this@ManagerReportsActivity, BranchActivity::class.java))
                    return@OnNavigationItemSelectedListener true

                }
                R.id.logout -> {

                }
            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            Log.i(ContentValues.TAG, "onNavigationItemSelected: nothing clicked")
            false
        })

        binding.bottomNav.selectedItemId = R.id.reports

        binding.bottomNav.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu -> {
                    startActivity(Intent(applicationContext, ManagerMenuActivity::class.java))
                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.reports -> return@OnNavigationItemSelectedListener true

                R.id.sales -> {
                    startActivity(Intent(applicationContext, ManagerSalesActivity::class.java))
                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }

            }
            false
        })
    }
}