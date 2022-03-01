package com.example.newsapp.data.repository

import com.example.newsapp.data.remote.RetrofitInstance
import com.example.newsapp.data.local.ArticleDatabase
import com.example.newsapp.domain.models.Article

class NewsRepository(
    private val db: ArticleDatabase
) {
    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.newsAPI.getBreakingNews(countryCode, pageNumber)

    suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        RetrofitInstance.newsAPI.searchForNews(searchQuery, pageNumber)

    /**
     * Methods related to the room database
     * */
    suspend fun upsert(article: Article) = db.getArticleDao().upsert(article)

    suspend fun deleteArticle(article: Article) = db.getArticleDao().deleteArticle(article)

    fun getSavedNews() = db.getArticleDao().getAllArticles()
}