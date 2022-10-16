package com.movosoft.janari.chef

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.movosoft.janari.R
import com.movosoft.janari.chef.adapters.ChefFragmentAdapter
import com.movosoft.janari.databinding.ActivityChefReportsBinding

class ChefReportsActivity : AppCompatActivity() {
    private var adapter: ChefFragmentAdapter? = null
    lateinit var binding: ActivityChefReportsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChefReportsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Graphs"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Lists"))

        val fragmentManager = supportFragmentManager
        adapter = ChefFragmentAdapter(fragmentManager, lifecycle)
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

        binding.bottomNav.selectedItemId = R.id.reports

        binding.bottomNav.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.confirmed -> {
                    startActivity(Intent(applicationContext, ChefConfirmedActivity::class.java))
                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.menu -> {
                    startActivity(Intent(applicationContext, ChefMenuActivity::class.java))
                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.reports -> return@OnNavigationItemSelectedListener true

                R.id.newOrders -> {
                    startActivity(Intent(applicationContext, ChefOrdersActivity::class.java))
                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }

            }
            false
        })
    }
}