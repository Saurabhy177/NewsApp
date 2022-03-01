package com.example.newsapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.newsapp.R
import com.example.newsapp.data.local.ArticleDatabase
import com.example.newsapp.data.repository.NewsRepository
import com.example.newsapp.presentation.viewmodels.NewsViewModel
import com.example.newsapp.presentation.viewmodels.NewsViewModelProviderFactory
import kotlinx.android.synthetic.main.activity_news.*

class NewsActivity : AppCompatActivity() {

    lateinit var newsViewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val newsRepository = NewsRepository(ArticleDatabase(this))
        val viewModelProviderFactory = NewsViewModelProviderFactory(application, newsRepository)
        newsViewModel = ViewModelProvider(this, viewModelProviderFactory)
            .get(NewsViewModel::class.java)

        // Setting content view after instantiating news view model
        setContentView(R.layout.activity_news)

        // Setting flow of bottom menu fragments using nav graph
        bottomNavigationView.setupWithNavController(newsNavHostFragment.findNavController())
    }
}