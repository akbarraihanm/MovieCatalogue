package com.example.moviecatalogue.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvShow(
    var pict : Int?,
    var title : String?,
    var detailTvShow : String?,
    var status : String?,
    var runtime : String?
) : Parcelable