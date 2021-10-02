package com.example.newsapp.api

import com.example.newsapp.util.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    companion object {

        private val retrofit by lazy {
            /**
             * Attaching a logging interceptor to be able to see api request/ response
             * for debugging and Level.BODY means we'll see the actual body of the response.
             * */
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

            /**
             * Specifying the converter factory to be used for interpreting and
             * converting the response into kotlin objects
             * */
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }

        val newsAPI by lazy {
            retrofit.create(NewsAPI::class.java)
        }
    }
}