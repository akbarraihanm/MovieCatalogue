package com.example.moviecatalogue.tvshows

import android.content.Context
import android.widget.Toast
import com.example.moviecatalogue.model.ResponseTvShow
import com.example.moviecatalogue.model.TvShow
import com.example.moviecatalogue.service.ApiClient
import com.example.moviecatalogue.service.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvShowsPresenter (private val context: Context, private val tvShowView: TvShowView) {

    fun getTvShowItems(language : String){
        val apiInterface = ApiClient.getApi().create(ApiInterface::class.java)
        val callTvShow = apiInterface.getTvShow(language)
        var listTvShow : ArrayList<TvShow>

        callTvShow.enqueue(object : Callback<ResponseTvShow>{
            override fun onFailure(call: Call<ResponseTvShow>, t: Throwable) {
                Toast.makeText(context, "Tidak ada koneksi", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<ResponseTvShow>, response: Response<ResponseTvShow>) {
                listTvShow = response.body()?.resultListTvShow ?: arrayListOf()
                try {
                    tvShowView.showListTvShows(listTvShow)
                }catch (e:Exception){}
            }

        })
    }
}