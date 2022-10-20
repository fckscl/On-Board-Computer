package com.example.on_boardcomputer.util
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.on_boardcomputer.ui.display.DisplayStateFragment
import com.example.on_boardcomputer.ui.main.MainFragment

class PageAdapter(activity: FragmentActivity, private val tabCount: Int) : FragmentStateAdapter(activity)
{
    override fun getItemCount(): Int = tabCount

    override fun createFragment(position: Int): Fragment
    {
        return when (position)
        {
            0 -> MainFragment()
            1 -> DisplayStateFragment()
            2 -> SettingsFragment()
            else -> MainFragment()
        }
    }

}