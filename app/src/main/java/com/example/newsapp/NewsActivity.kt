package com.example.newsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.newsapp.databinding.ActivityMainBinding
import com.example.newsapp.db.ArticleDatabase
import com.example.newsapp.ui.NewsRepository
import com.example.newsapp.ui.NewsViewModel
import com.example.newsapp.ui.NewsViewModelProviderFactory

class NewsActivity : AppCompatActivity() {

    lateinit var viewModel: NewsViewModel
    private lateinit var activityNewsBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityNewsBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityNewsBinding.root)
        init()

        val newsRepo = NewsRepository(ArticleDatabase(this))
        val newsViewModelProviderFactory = NewsViewModelProviderFactory(newsRepo)
        viewModel = ViewModelProvider(this, newsViewModelProviderFactory)[NewsViewModel::class.java]
    }

    private fun init() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(activityNewsBinding.newsNavHostFragment.id) as NavHostFragment
        val navController = navHostFragment.navController
        activityNewsBinding.bottomNavigationView.setupWithNavController(navController)
    }
}