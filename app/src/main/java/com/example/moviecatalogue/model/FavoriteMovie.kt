package com.example.moviecatalogue.model

import android.database.Cursor
import android.os.Parcelable
import android.provider.BaseColumns._ID
import com.example.moviecatalogue.favorites.db2.DbContract.Companion.getColumnInt
import com.example.moviecatalogue.favorites.db2.DbContract.Companion.getColumnString
import com.example.moviecatalogue.favorites.db2.DbContract.FavMovieColumn.Companion.ID_MOVIE
import com.example.moviecatalogue.favorites.db2.DbContract.FavMovieColumn.Companion.LINK_POSTER
import com.example.moviecatalogue.favorites.db2.DbContract.FavMovieColumn.Companion.POSTER_PATH
import com.example.moviecatalogue.favorites.db2.DbContract.FavMovieColumn.Companion.TITLE_MOVIE
import kotlinx.android.parcel.Parcelize


@Parcelize
data class FavoriteMovie (
    var id : Int? = 0 ,
    var id_movie : String? = null,
    var title_movie : String? = null,
    var link_poster : String? = null,
    var poster_path : String? = null
) : Parcelable {

    constructor(cursor: Cursor) : this(){
        this.id = getColumnInt(cursor, _ID)
        this.id_movie = getColumnString(cursor, ID_MOVIE)
        this.title_movie = getColumnString(cursor, TITLE_MOVIE)
        this.link_poster = getColumnString(cursor, LINK_POSTER)
        this.poster_path = getColumnString(cursor, POSTER_PATH)
    }
}