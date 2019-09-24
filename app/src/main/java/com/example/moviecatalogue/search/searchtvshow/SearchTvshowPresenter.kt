package com.example.moviecatalogue.search.searchtvshow

import com.example.moviecatalogue.model.ResponseTvShow
import com.example.moviecatalogue.service.ApiClient
import com.example.moviecatalogue.service.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchTvshowPresenter(private val searchTvshowView : SearchTvshowView) {

    fun getSearchTvShow(language : String, query : String){
        searchTvshowView.showLoading()
        val apiInterface = ApiClient.getSearchItem().create(ApiInterface::class.java)
        val call = apiInterface.getSearchTvShow(language, query)

        call.clone().enqueue(object : Callback<ResponseTvShow>{

            override fun onFailure(call: Call<ResponseTvShow>, t: Throwable) {
                searchTvshowView.toastNoConnection()
            }

            override fun onResponse(call: Call<ResponseTvShow>, response: Response<ResponseTvShow>) {
                val listTvShow = response.body()?.resultListTvShow
                try {
                    listTvShow?.let { searchTvshowView.showTvShowData(it) }
                    searchTvshowView.stopLoading()
                }catch (e:Exception){

                }
            }

        })
    }

}