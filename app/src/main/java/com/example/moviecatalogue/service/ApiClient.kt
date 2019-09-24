package com.example.moviecatalogue.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    companion object{

        fun getApi() : Retrofit {
            val baseUrl = "https://api.themoviedb.org/3/discover/"
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        fun getDetailMovie() : Retrofit {
            val baseUrl = "https://api.themoviedb.org/3/movie/"
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        fun getDetailTvShow() : Retrofit {
            val baseUrl = "https://api.themoviedb.org/3/tv/"
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        fun getSearchItem() : Retrofit {
            val baseUrl = "https://api.themoviedb.org/3/search/"
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}