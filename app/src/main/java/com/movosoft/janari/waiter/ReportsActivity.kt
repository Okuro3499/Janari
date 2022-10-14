package com.movosoft.janari.waiter

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.movosoft.janari.R
import com.movosoft.janari.waiter.adapters.FragmentAdapter

class ReportsActivity : AppCompatActivity() {
    private var adapter: FragmentAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reports)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        val tab = findViewById<TabLayout>(R.id.tabLayout)
        val viewPager2 = findViewById<ViewPager2>(R.id.viewPager2)

        tab.addTab(tab.newTab().setText("Graphs"))
        tab.addTab(tab.newTab().setText("Lists"))

        val fragmentManager = supportFragmentManager
        adapter = FragmentAdapter(fragmentManager, lifecycle)
        viewPager2.adapter = adapter

        tab.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager2.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        viewPager2.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                tab.selectTab(tab.getTabAt(position))
            }
        })

        bottomNav.selectedItemId = R.id.reports

        bottomNav.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
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