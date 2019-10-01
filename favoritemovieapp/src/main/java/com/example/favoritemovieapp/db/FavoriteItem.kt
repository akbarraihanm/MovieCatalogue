package com.example.favoritemovieapp.db

import android.database.Cursor
import android.provider.BaseColumns
import com.example.favoritemovieapp.db.DbContract.Companion.getColumnInt
import com.example.favoritemovieapp.db.DbContract.Companion.getColumnString
import com.example.favoritemovieapp.db.DbContract.FavMovieColumn.Companion.ID_MOVIE
import com.example.favoritemovieapp.db.DbContract.FavMovieColumn.Companion.TITLE_MOVIE

data class FavoriteItem (
    var id : Int? = 0,
    var id_movie : String? = null,
    var title_movie : String? = null
) {
    constructor(cursor: Cursor) : this(){
        this.id = getColumnInt(cursor, BaseColumns._ID)
        this.id_movie = getColumnString(cursor, ID_MOVIE)
        this.title_movie = getColumnString(cursor, TITLE_MOVIE)
    }
}