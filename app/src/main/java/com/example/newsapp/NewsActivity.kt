package com.example.newsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.newsapp.databinding.ActivityMainBinding

class NewsActivity : AppCompatActivity() {

    private lateinit var activityNewsBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityNewsBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityNewsBinding.root)
        init()
    }

    private fun init() {
        val navHostFragment = supportFragmentManager.findFragmentById(activityNewsBinding.newsNavHostFragment.id) as NavHostFragment
        val navController = navHostFragment.navController
        activityNewsBinding.bottomNavigationView.setupWithNavController(navController)
    }
}