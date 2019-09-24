package com.example.moviecatalogue.search.searchmovie

import com.example.moviecatalogue.model.ResponseMovie
import com.example.moviecatalogue.service.ApiClient
import com.example.moviecatalogue.service.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchMoviePresenter(private val searchMovieView : SearchMovieView) {

    fun getMovieItem(language : String, query : String){

        searchMovieView.showLoading()

        val apiInterface = ApiClient.getSearchItem().create(ApiInterface::class.java)
        val call = apiInterface.getSearchMovie(language, query)
        call.clone().enqueue(object : Callback<ResponseMovie>{
            override fun onFailure(call: Call<ResponseMovie>, t: Throwable) {
                searchMovieView.toastNoConnection()
            }

            override fun onResponse(call: Call<ResponseMovie>, response: Response<ResponseMovie>) {
                val listMovie = response.body()?.resultListMovie
                try {
                    listMovie?.let { searchMovieView.showSearchMovieData(it) }
                    searchMovieView.stopLoading()
                }catch (e:Exception){

                }
            }

        })
    }

}