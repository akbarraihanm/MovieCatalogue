package com.example.moviecatalogue.favorites.db2

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns._ID
import com.example.moviecatalogue.favorites.db2.DbContract.Companion.TABLE_MOVIE
import com.example.moviecatalogue.favorites.db2.DbContract.FavMovieColumn.Companion.ID_MOVIE
import com.example.moviecatalogue.favorites.db2.DbContract.FavMovieColumn.Companion.TITLE_MOVIE
import com.example.moviecatalogue.model.FavoriteMovie
import java.sql.SQLException

class FavMovieHelper(private val context: Context) {

    companion object{
        val DATABASE_TABLE = TABLE_MOVIE
    }

    private lateinit var dbHelper : DbHelper
    private lateinit var database : SQLiteDatabase

    @Throws(SQLException::class)
    fun open() : FavMovieHelper {
        dbHelper = DbHelper(context)
        database = dbHelper.writableDatabase
        return this
    }

    fun close() {
        dbHelper.close()
    }

    fun insert(favMovie : FavoriteMovie) : Long{
        val values = ContentValues()
        values.put(ID_MOVIE, favMovie.id_movie)
        values.put(TITLE_MOVIE, favMovie.title_movie)

        return database.insert(TABLE_MOVIE, null, values)
    }

    fun query() : ArrayList<FavoriteMovie> {
        val listFavMovie = arrayListOf<FavoriteMovie>()
        val cursor = database.query(DATABASE_TABLE,
            null,
            null,
            null,
            null,
            null,
            "$_ID DESC", null
        )
        cursor.moveToFirst()
        var favMovie : FavoriteMovie
        if(cursor.count > 0){
            do{
                favMovie = FavoriteMovie()
                favMovie.id = cursor.getInt(cursor.getColumnIndexOrThrow(_ID))
                favMovie.id_movie = cursor.getString(cursor.getColumnIndexOrThrow(ID_MOVIE))
                favMovie.title_movie = cursor.getString(cursor.getColumnIndexOrThrow(TITLE_MOVIE))

                listFavMovie.add(favMovie)
                cursor.moveToNext()
            } while (!cursor.isAfterLast)
        }
        cursor.close()
        return listFavMovie
    }

    fun check(id_movie : String) : Boolean {
        val cursor = database.rawQuery("SELECT * FROM $DATABASE_TABLE WHERE $ID_MOVIE = $id_movie", null)
        return if(cursor.count <= 0){
            cursor.close()
            false
        }else{
            cursor.close()
            true
        }
    }

    fun delete(id_movie : String) : Int {
        return database.delete(TABLE_MOVIE, "$ID_MOVIE = $id_movie", null)
    }

}