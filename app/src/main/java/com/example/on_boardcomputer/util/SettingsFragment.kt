package com.example.on_boardcomputer.util

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.example.on_boardcomputer.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}