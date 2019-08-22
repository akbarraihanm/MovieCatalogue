package com.example.moviecatalogue.detailmovie

import android.content.Context
import android.widget.Toast
import com.example.moviecatalogue.model.DetailMovie
import com.example.moviecatalogue.service.ApiClient
import com.example.moviecatalogue.service.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailMoviePresenter (private val context: Context, private val detailMovieView : DetailMovieView) {

    fun getDetailMovieData(id : String, language : String){

        val apiInterface = ApiClient.getDetailMovie().create(ApiInterface::class.java)
        val callDetailMovie = apiInterface.getDetailMovie(id, language)
        var detailMovieData: DetailMovie?

        callDetailMovie.enqueue(object : Callback<DetailMovie>{
            override fun onFailure(call: Call<DetailMovie>, t: Throwable) {
                Toast.makeText(context, "Tidak ada koneksi", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<DetailMovie>, response: Response<DetailMovie>) {
                detailMovieData = response.body()
                try {
                    detailMovieData?.let { detailMovieView.showDetailMovieItem(it) }
                }catch (e:Exception){}
            }

        })
    }
}