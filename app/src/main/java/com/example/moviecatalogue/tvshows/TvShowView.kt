package com.example.moviecatalogue.tvshows

import com.example.moviecatalogue.model.TvShow

interface TvShowView {
    fun showListTvShows(listTvShow : ArrayList<TvShow>)
}