package com.example.sherbek791

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.sherbek791.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class  MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController


    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val bottomNavView = findViewById<BottomNavigationView>(R.id.bottom_nav_bar)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController

        // Connect BottomNavigationView with NavController
        NavigationUI.setupWithNavController(bottomNavView, navController)


        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setSupportActionBar(binding.toolbar);
        binding.toolbar.navigationIcon = null
        binding.toolbar.title = "Wallpaper"

    }
    fun setBottomNavVisibility(visibility: Int) {
        findViewById<BottomNavigationView>(R.id.bottom_nav_bar).visibility = visibility
    }

    fun returnSearch() : ImageView{
        return binding.search
    }
}