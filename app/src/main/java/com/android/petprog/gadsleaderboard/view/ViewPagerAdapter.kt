package com.android.petprog.gadsleaderboard.view

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.android.petprog.gadsleaderboard.view.learning.LearningLeadersFragment
import com.android.petprog.gadsleaderboard.view.skilliq.SkillIQLeadersFragment


class ViewPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return when (position) {
            0 -> LearningLeadersFragment()
            else -> SkillIQLeadersFragment()
        }

    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Learning Leaders"
            else -> "SkillIQ Leaders"
        }
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return 2
    }
}