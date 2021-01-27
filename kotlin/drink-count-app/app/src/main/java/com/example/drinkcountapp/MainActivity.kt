package com.example.drinkcountapp

import android.os.Bundle
import androidx.work.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.drinkcountapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //Add top bar
        setSupportActionBar(binding.topBar)

        //Navigation controller
        val navController = findNavController(R.id.nav_host_fragment)

        //App bar configuration for navigation drawer
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.mainPage,
                R.id.drinkHistoryFragment,
            ), binding.drawerLayout
        )

        //Set up navigation controller
        binding.navView.setupWithNavController(navController)
        //binding.navView.itemTextColor = getColorStateList(R.drawable.nav_color)

        //Activate drawer toggle
        toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            R.string.open,
            R.string.close
        )
        binding.lifecycleOwner=this
    }

}