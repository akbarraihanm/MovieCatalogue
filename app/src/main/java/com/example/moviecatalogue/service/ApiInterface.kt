package com.example.moviecatalogue.service

import com.example.moviecatalogue.model.DetailMovie
import com.example.moviecatalogue.model.DetailTvShow
import com.example.moviecatalogue.model.ResponseMovie
import com.example.moviecatalogue.model.ResponseTvShow
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("movie?api_key=8c7287fb84069e97aeb107a1109611ed")
    fun getMovie(@Query("language") lang : String) : Call<ResponseMovie>

    @GET("tv?api_key=8c7287fb84069e97aeb107a1109611ed")
    fun getTvShow(@Query("language") lang : String) : Call<ResponseTvShow>

    @GET("{id}?api_key=8c7287fb84069e97aeb107a1109611ed")
    fun getDetailMovie(@Path("id") idMovie : String,
                       @Query("language") lang : String) : Call<DetailMovie>

    @GET("{id}?api_key=8c7287fb84069e97aeb107a1109611ed")
    fun getDetailTvShow(@Path("id") idTvShow : String,
                        @Query("language") lang : String) : Call<DetailTvShow>

    @GET("movie?api_key=8c7287fb84069e97aeb107a1109611ed")
    fun getSearchMovie(@Query("language") lang: String,
                       @Query("query") query : String) : Call<ResponseMovie>

    @GET("tv?api_key=8c7287fb84069e97aeb107a1109611ed")
    fun getSearchTvShow(@Query("language") lang : String,
                        @Query("query") query: String) : Call<ResponseTvShow>

    @GET("movie?api_key=8c7287fb84069e97aeb107a1109611ed")
    fun getReleasedToday(@Query("language") lang: String,
                         @Query("primary_release_date.gte") dateGte : String,
                         @Query("primary_release_date.lte") dateLte : String) : Call<ResponseMovie>
}