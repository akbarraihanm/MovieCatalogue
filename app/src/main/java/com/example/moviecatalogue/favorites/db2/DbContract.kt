package com.example.moviecatalogue.favorites.db2

import android.database.Cursor
import android.provider.BaseColumns

open class KBaseColumns  {
    val _ID = "_id"
}


class DbContract {
    companion object{
        val TABLE_MOVIE = "FavMovie"
        val TABLE_TVSHOW = "FavTvShow"
    }

    class FavMovieColumn : BaseColumns {
        companion object : KBaseColumns() {
            const val ID_MOVIE = "idmovie"
            const val TITLE_MOVIE = "titlemovie"
        }
    }

    class FavTvShowColumn : BaseColumns {
        companion object : KBaseColumns() {
            const val ID_TVSHOW = "idtvshow"
            const val TITLE_TVSHOW = "tvshow"
        }
    }
}