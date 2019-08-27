package com.example.moviecatalogue.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class FavoriteMovie (
    var id : Int? = 0 ,
    var id_movie : String? = null,
    var title_movie : String? = null
) : Parcelable