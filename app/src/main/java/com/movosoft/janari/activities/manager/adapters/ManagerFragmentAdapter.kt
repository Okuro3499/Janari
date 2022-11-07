package com.movosoft.janari.activities.manager.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.movosoft.janari.activities.manager.fragments.ManagerGraphsFragment

class ManagerFragmentAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun createFragment(position: Int): Fragment {
        return if (position == 1) {
            ManagerGraphsFragment()
        } else ManagerGraphsFragment()
    }

    override fun getItemCount(): Int {
        return 2
    }
}