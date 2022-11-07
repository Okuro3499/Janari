package com.movosoft.janari.activities.cashier

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.movosoft.janari.R
import com.movosoft.janari.activities.cashier.adapters.CashierFragmentAdapter
import com.movosoft.janari.databinding.ActivityCashierReportsBinding

class CashierReportsActivity : AppCompatActivity() {
    private var adapter: CashierFragmentAdapter? = null
    lateinit var binding: ActivityCashierReportsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCashierReportsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Graphs"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Lists"))

        val fragmentManager = supportFragmentManager
        adapter = CashierFragmentAdapter(fragmentManager, lifecycle)
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
                R.id.menu -> {
                    startActivity(Intent(applicationContext, CashierMenuActivity::class.java))
                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.reports -> return@OnNavigationItemSelectedListener true

                R.id.sales-> {
                    startActivity(Intent(applicationContext, CashierSalesActivity::class.java))
                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }

            }
            false
        })
    }
}