package com.movosoft.janari.cashier.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.movosoft.janari.cashier.fragments.CashierGraphsFragment

class CashierFragmentAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun createFragment(position: Int): Fragment {
        return if (position == 1) {
            CashierGraphsFragment()
        } else CashierGraphsFragment()
    }

    override fun getItemCount(): Int {
        return 2
    }
}