package com.example.moviecatalogue.detailtvshow

import android.content.Context
import android.widget.Toast
import com.example.moviecatalogue.model.DetailTvShow
import com.example.moviecatalogue.service.ApiClient
import com.example.moviecatalogue.service.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailTvShowPresenter (private val context: Context, private val detailTvShowView : DetailTvShowView) {
    fun getDataDetailTvShow(id : String, language : String){
        val apiInterface = ApiClient.getDetailTvShow().create(ApiInterface::class.java)
        val callData = apiInterface.getDetailTvShow(id, language)
        var dataDetailTvShow : DetailTvShow?

        callData.enqueue(object : Callback<DetailTvShow>{
            override fun onFailure(call: Call<DetailTvShow>, t: Throwable) {
                Toast.makeText(context, "Tidak ada koneksi", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<DetailTvShow>, response: Response<DetailTvShow>) {
                dataDetailTvShow = response.body()
                try {
                    dataDetailTvShow?.let { detailTvShowView.showDataDetailTvShow(it) }
                }catch (e:Exception){}
            }

        })
    }
}