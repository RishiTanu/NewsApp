package com.example.newsapp.ui

import com.example.newsapp.api.RetrofitInstance
import com.example.newsapp.db.ArticleDatabase

class NewsRepository (
    private val articleDatabase: ArticleDatabase
){
    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getBreakingNews(countryCode, pageNumber)

    suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        RetrofitInstance.api.searchForNews(searchQuery, pageNumber)
}