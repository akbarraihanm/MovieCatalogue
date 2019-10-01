package com.example.favoritemovieapp.db

import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.provider.BaseColumns

open class KBaseColumns  {
    val _ID = "_id"
}


class DbContract {

    companion object{
        val TABLE_MOVIE = "FavMovie"
        val TABLE_TVSHOW = "FavTvShow"
        val AUTHORITY = "com.example.moviecatalogue"

        fun getColumnString(cursor: Cursor, columnName: String): String {
            return cursor.getString(cursor.getColumnIndex(columnName))
        }

        fun getColumnInt(cursor: Cursor, columnName: String): Int {
            return cursor.getInt(cursor.getColumnIndex(columnName))
        }

        fun getColumnLong(cursor: Cursor, columnName: String): Long {
            return cursor.getLong(cursor.getColumnIndex(columnName))
        }
    }

    class FavMovieColumn : BaseColumns {
        companion object : KBaseColumns() {
            const val ID_MOVIE = "idmovie"
            const val TITLE_MOVIE = "titlemovie"
            const val LINK_POSTER = "linkposter"
            const val POSTER_PATH = "posterpath"
            val CONTENT_URI = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Uri.Builder().scheme("content")
                    .authority(AUTHORITY)
                    .appendPath(TABLE_MOVIE)
                    .build()
            } else {
                TODO("VERSION.SDK_INT < N")
            }
        }
    }

}