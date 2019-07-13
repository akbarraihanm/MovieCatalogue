package com.example.moviecatalogue.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ListMovie(
        var pict : Int?,
        var name : String?,
        var detailPoster : String?,
        var status : String?,
        var budget : String?
    ) : Parcelable