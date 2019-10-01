package com.example.moviecatalogue.model

import com.google.gson.annotations.SerializedName

data class DetailTvShow (
    var linkPoster : String? = "https://image.tmdb.org/t/p/w185/",
    @SerializedName("poster_path")
    var posterPath : String? = null,
    @SerializedName("id")
    var idTvShow : String? = null,
    @SerializedName("name")
    var nameTvShow : String? = null,
    @SerializedName("status")
    var statusTvShow : String? = null,
    @SerializedName("episode_run_time")
    var runTime : ArrayList<String>,
    @SerializedName("overview")
    var overview : String? = null
)