package com.example.moviecatalogue.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ListMovie(
        var linkPoster: String = "https://image.tmdb.org/t/p/w185/",
        @SerializedName("title")
        var title : String? = null,
        @SerializedName("poster_path")
        var posterPath : String? = null,
        @SerializedName("id")
        var idMovie : String? = null
) : Parcelable