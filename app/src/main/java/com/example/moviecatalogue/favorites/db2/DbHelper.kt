package com.example.moviecatalogue.favorites.db2

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object{
        val DB_NAME = "Favorite"
        val DB_VERSION = 1
        val createTableFavMovie = String.format("CREATE TABLE %s" +
                " (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                " %s TEXT NOT NULL," +
                " %s TEXT NOT NULL)",
            DbContract.TABLE_MOVIE,
            DbContract.FavMovieColumn._ID,
            DbContract.FavMovieColumn.ID_MOVIE,
            DbContract.FavMovieColumn.TITLE_MOVIE
        )
        val createTableFavTvShow = String.format("CREATE TABLE %s" +
                " (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                " %s TEXT NOT NULL," +
                " %s TEXT NOT NULL)",
            DbContract.TABLE_TVSHOW,
            DbContract.FavTvShowColumn._ID,
            DbContract.FavTvShowColumn.ID_TVSHOW,
            DbContract.FavTvShowColumn.TITLE_TVSHOW
        )
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(createTableFavMovie)
        db?.execSQL(createTableFavTvShow)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS "+DbContract.TABLE_MOVIE)
        db?.execSQL("DROP TABLE IF EXISTS "+DbContract.TABLE_TVSHOW)
        onCreate(db)
    }
}