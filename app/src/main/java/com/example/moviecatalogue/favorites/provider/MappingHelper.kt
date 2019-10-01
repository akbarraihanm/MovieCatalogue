package com.example.moviecatalogue.favorites.provider

import android.database.Cursor
import android.provider.BaseColumns._ID
import com.example.moviecatalogue.favorites.db2.DbContract.FavMovieColumn.Companion.ID_MOVIE
import com.example.moviecatalogue.favorites.db2.DbContract.FavMovieColumn.Companion.LINK_POSTER
import com.example.moviecatalogue.favorites.db2.DbContract.FavMovieColumn.Companion.POSTER_PATH
import com.example.moviecatalogue.favorites.db2.DbContract.FavMovieColumn.Companion.TITLE_MOVIE
import com.example.moviecatalogue.model.FavoriteMovie

class MappingHelper {

    fun mapCursorToArrayList(movieCursor : Cursor) : ArrayList<FavoriteMovie>{
        val listFavMovie = arrayListOf<FavoriteMovie>()

        while (movieCursor.moveToNext()){
            val id = movieCursor.getInt(movieCursor.getColumnIndexOrThrow(_ID))
            val id_movie = movieCursor.getString(movieCursor.getColumnIndexOrThrow(ID_MOVIE))
            val title_movie = movieCursor.getString(movieCursor.getColumnIndexOrThrow(TITLE_MOVIE))
            val link_poster = movieCursor.getString(movieCursor.getColumnIndexOrThrow(LINK_POSTER))
            val poster_path = movieCursor.getString(movieCursor.getColumnIndexOrThrow(POSTER_PATH))
            listFavMovie.add(FavoriteMovie(id, id_movie, title_movie, link_poster, poster_path))
        }
        return listFavMovie
    }

}