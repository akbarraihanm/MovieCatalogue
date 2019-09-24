package com.example.moviecatalogue.search.searchmovie

import com.example.moviecatalogue.model.ListMovie

interface SearchMovieView {
    fun showSearchMovieData(listMovie : ArrayList<ListMovie>)
    fun showLoading()
    fun stopLoading()
    fun toastNoConnection()
}
