package ru.kholmogorova.myapplication.pl

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.kholmogorova.myapplication.pl.fragments.FriendsFragment
import ru.kholmogorova.myapplication.pl.fragments.HistoryFragment
import ru.kholmogorova.myapplication.pl.fragments.MainFragment

class TabsPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MainFragment()
            1 -> HistoryFragment()
            2 -> FriendsFragment()
            else -> throw IllegalArgumentException("Invalid position $position")
        }
    }
}