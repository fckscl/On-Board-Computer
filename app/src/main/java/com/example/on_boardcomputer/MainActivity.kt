package com.example.on_boardcomputer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.on_boardcomputer.databinding.ActivityMainBinding
import com.example.on_boardcomputer.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
//        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main, MainFragment.newInstance())
                .commitNow()
        }

        setSupportActionBar(findViewById(R.id.action_bar))

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.action_bar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(R.id.myNavHostFragment)
//        navController.navigate(R.id.action_fragment_display_state_to_settingsFragment)
        return NavigationUI.onNavDestinationSelected(item,
            navController)
                || super.onOptionsItemSelected(item)
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            R.id.settings -> {
//                // User chose the "Settings" item, show the app settings UI...
//                Log.i("set", "settings")
//                val navController = findNavController(R.id.myNavHostFragment)
//                navController.navigate(R.id.action_fragment_display_state_to_settingsFragment)
////                view.findNavController
////                findNavController().navigate(R.id.action_main_to_fragment_display_state)
////                val actListLauncher: ActivityResultLauncher<Intent> = RegisterForActivity()
////                actListLauncher.launch(Intent(this, SettingsFragment::class.java))
//            }
//            else -> {
//                // If we got here, the user's action was not recognized.
//                // Invoke the superclass to handle it.
//                super.onOptionsItemSelected(item)
//            }
//        }
//        return true
//    }
}