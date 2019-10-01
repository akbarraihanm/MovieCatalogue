package com.example.moviecatalogue.favorites.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import android.os.Build
import com.example.moviecatalogue.favorites.db2.DbContract.Companion.AUTHORITY
import com.example.moviecatalogue.favorites.db2.DbContract.Companion.TABLE_MOVIE
import com.example.moviecatalogue.favorites.db2.DbContract.FavMovieColumn.Companion.CONTENT_URI
import com.example.moviecatalogue.favorites.db2.FavMovieHelper


class MovieProvider : ContentProvider() {

    lateinit var favMovieHelper: FavMovieHelper

    companion object{
        var MOVIE = 1
        var MOVIE_ID = 2
        val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)
        init {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                uriMatcher.addURI(AUTHORITY, TABLE_MOVIE, MOVIE)
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                uriMatcher.addURI(AUTHORITY, TABLE_MOVIE, MOVIE_ID)
            }
        }
    }

    override fun insert(uri: Uri, contentValues: ContentValues?): Uri? {
        favMovieHelper.open()
        val added : Long? = when(uriMatcher.match(uri)){
            MOVIE ->{
                contentValues?.let { favMovieHelper.insertProvider(it) }
            }
            else -> 0
        }

        if (added != null) {
            if (added > 0){
                context?.contentResolver?.notifyChange(uri, null)
            }
        }

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Uri.parse("$CONTENT_URI/$added")
        } else {
            TODO("VERSION.SDK_INT < LOLLIPOP")
        }
    }

    override fun query(
        uri: Uri,
        strings: Array<String>?,
        s: String?,
        strings1: Array<String>?,
        s1: String?
    ): Cursor? {
        favMovieHelper.open()
        return when(uriMatcher.match(uri)){
            MOVIE ->{
                favMovieHelper.queryProvider()
            }
            MOVIE_ID -> {
                favMovieHelper.queryByIdProvider(uri.lastPathSegment!!)
            }
            else -> null
        }
    }

    override fun onCreate(): Boolean {
        favMovieHelper = FavMovieHelper(context!!)
        favMovieHelper.open()
        return true
    }

    override fun update(p0: Uri, p1: ContentValues?, p2: String?, p3: Array<String>?): Int {
        return 0
    }

    override fun delete(uri: Uri, s: String?, strings: Array<String>?): Int {
        favMovieHelper.open()
        val deleted : Int? = when(uriMatcher.match(uri)){
            MOVIE_ID ->{
                favMovieHelper.deleteProvider(uri.lastPathSegment!!)
            }
            else -> 0
        }

        if (deleted != null) {
            if(deleted > 0){
                context?.contentResolver?.notifyChange(uri, null)
            }
        }

        return deleted!!
    }

    override fun getType(p0: Uri): String? {
        return null
    }
}