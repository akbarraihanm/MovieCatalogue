package com.example.moviecatalogue.movies

import com.example.moviecatalogue.model.ListMovie

interface MoviesView {
    fun showMoviesItem(listMovies : ArrayList<ListMovie>)
}