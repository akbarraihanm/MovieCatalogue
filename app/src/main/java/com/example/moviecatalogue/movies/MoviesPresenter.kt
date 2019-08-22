package com.example.moviecatalogue.movies

import android.content.Context
import android.widget.Toast
import com.example.moviecatalogue.model.ListMovie
import com.example.moviecatalogue.model.ResponseMovie
import com.example.moviecatalogue.service.ApiClient
import com.example.moviecatalogue.service.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesPresenter (private val context: Context, private val moviesView: MoviesView) {
    fun getListMovieItems(language : String){
        val apiInterface = ApiClient.getApi().create(ApiInterface::class.java)
        val callListMovie = apiInterface.getMovie(language)
        var listMovie : ArrayList<ListMovie>
        callListMovie.enqueue(object : Callback<ResponseMovie>{
            override fun onFailure(call: Call<ResponseMovie>, t: Throwable) {
                Toast.makeText(context, "Tidak ada koneksi", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<ResponseMovie>, response: Response<ResponseMovie>) {
                listMovie = response.body()?.resultListMovie ?: arrayListOf()
                try {
                    if (response.isSuccessful){
                        moviesView.showMoviesItem(listMovie)
                    }
                }catch (e:Exception){}
            }
        })
    }
}