package com.example.moviecatalogue.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FavoriteTvShow(
    var id : Int? = 0,
    var id_tvshow : String? = null,
    var title_tvshow : String? = null,
    var link_poster : String? = null,
    var poster_path : String? = null
) : Parcelable