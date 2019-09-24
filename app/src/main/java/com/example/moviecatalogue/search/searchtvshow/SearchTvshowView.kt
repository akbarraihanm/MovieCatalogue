package com.example.moviecatalogue.search.searchtvshow

import com.example.moviecatalogue.model.TvShow

interface SearchTvshowView {

    fun showTvShowData(listTvShow : ArrayList<TvShow>)
    fun showLoading()
    fun stopLoading()
    fun toastNoConnection()

}
