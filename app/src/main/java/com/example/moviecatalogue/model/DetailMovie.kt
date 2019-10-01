package com.example.moviecatalogue.model

import com.google.gson.annotations.SerializedName

data class DetailMovie (
    var linkPoster : String? = "https://image.tmdb.org/t/p/w185/",
    @SerializedName("status")
    var statusMovie : String? = null,
    @SerializedName("budget")
    var budgetMovie : String? = null,
    @SerializedName("overview")
    var overviewMovie : String? = null,
    @SerializedName("title")
    var titleMovie : String? = null,
    @SerializedName("poster_path")
    var posterPath : String? = null,
    @SerializedName("id")
    var idDetail : String? = null
)