package com.example.newsapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.newsapp.domain.models.Article

@Dao
interface ArticleDAO {

    /**
     * In case of a conflict like if an article already exists,
     * then we'll replace that article.
     * */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article): Long

    @Query("SELECT * FROM articles")
    fun getAllArticles(): LiveData<List<Article>>

    @Delete
    suspend fun deleteArticle(article: Article)
}