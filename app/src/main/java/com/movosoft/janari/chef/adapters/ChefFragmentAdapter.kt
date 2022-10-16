package com.movosoft.janari.chef.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.movosoft.janari.chef.fragments.ChefGraphsFragment

class ChefFragmentAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun createFragment(position: Int): Fragment {
        return if (position == 1) {
            ChefGraphsFragment()
        } else ChefGraphsFragment()
    }

    override fun getItemCount(): Int {
        return 2
    }
}