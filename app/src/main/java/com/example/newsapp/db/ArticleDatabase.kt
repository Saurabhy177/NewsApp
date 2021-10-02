package com.example.newsapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsapp.models.Article

/**
 * Since, the tables in room database can have only primitive datatype and
 * we have type "Source" in the article table.
 * Therefore, we need to create a custom type converter for it.
 * */
@Database(
    entities = [Article::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class ArticleDatabase: RoomDatabase() {

    abstract fun getArticleDao(): ArticleDAO

    companion object {
        /**
         * Volatile annotations means other threads can see if one thread
         * changes the database instance
         * */
        @Volatile
        private var instance: ArticleDatabase? = null

        /**
         * Using LOCK to make sure we have only one instance of the database
         * */
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ArticleDatabase::class.java,
                "article_db.db"
            ).build()
    }
}