package com.movosoft.janari.activities.waiter

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.movosoft.janari.R
import com.movosoft.janari.databinding.ActivityReportsBinding
import com.movosoft.janari.activities.waiter.adapters.FragmentAdapter

class ReportsActivity : AppCompatActivity() {
    lateinit var binding: ActivityReportsBinding
    private var adapter: FragmentAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReportsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Graphs"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Lists"))

        val fragmentManager = supportFragmentManager
        adapter = FragmentAdapter(fragmentManager, lifecycle)
        binding.viewPager2.adapter = adapter

        binding.tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                binding.viewPager2.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        binding.viewPager2.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position))
            }
        })

        binding.bottomNav.selectedItemId = R.id.reports

        binding.bottomNav.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu -> {
                    startActivity(
                        Intent(
                            applicationContext, MenuActivity::class.java
                        )
                    )
                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.reports -> return@OnNavigationItemSelectedListener true
                R.id.cart -> {
                    startActivity(
                        Intent(
                            applicationContext, CartActivity::class.java
                        )
                    )
                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        })
    }
}