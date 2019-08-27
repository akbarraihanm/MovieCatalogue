package com.example.moviecatalogue.favorites.db2

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns._ID
import com.example.moviecatalogue.favorites.db2.DbContract.Companion.TABLE_TVSHOW
import com.example.moviecatalogue.favorites.db2.DbContract.FavTvShowColumn.Companion.ID_TVSHOW
import com.example.moviecatalogue.favorites.db2.DbContract.FavTvShowColumn.Companion.TITLE_TVSHOW
import com.example.moviecatalogue.model.FavoriteTvShow
import java.sql.SQLException

class FavTvShowHelper(private val context: Context) {

    companion object{
        val DATABASE_TABLE = TABLE_TVSHOW
    }

    private lateinit var dbHelper : DbHelper
    private lateinit var database : SQLiteDatabase

    @Throws(SQLException::class)
    fun open() : FavTvShowHelper {
        dbHelper = DbHelper(context)
        database = dbHelper.writableDatabase
        return this
    }

    fun close() {
        dbHelper.close()
    }

    fun insert(favTvShow : FavoriteTvShow) : Long {
        val values = ContentValues()
        values.put(ID_TVSHOW, favTvShow.id_tvshow)
        values.put(TITLE_TVSHOW, favTvShow.title_tvshow)

        return database.insert(TABLE_TVSHOW, null, values)
    }

    fun query() : ArrayList<FavoriteTvShow> {
        val listFavTvShow = arrayListOf<FavoriteTvShow>()
        val cursor = database.query(
            DATABASE_TABLE,
            null,
            null,
            null,
            null,
            null,
            "$_ID DESC", null
        )
        cursor.moveToFirst()
        var favTvShow : FavoriteTvShow
        if(cursor.count > 0){
            do{
                favTvShow = FavoriteTvShow()
                favTvShow.id = cursor.getInt(cursor.getColumnIndexOrThrow(_ID))
                favTvShow.id_tvshow = cursor.getString(cursor.getColumnIndexOrThrow(ID_TVSHOW))
                favTvShow.title_tvshow = cursor.getString(cursor.getColumnIndexOrThrow(TITLE_TVSHOW))

                listFavTvShow.add(favTvShow)
                cursor.moveToNext()
            }while (!cursor.isAfterLast)
        }
        cursor.close()
        return listFavTvShow
    }

    fun check(id_tvshow : String) : Boolean {
        val cursor = database.rawQuery("SELECT * FROM $TABLE_TVSHOW WHERE $ID_TVSHOW = $id_tvshow", null)
        return if(cursor.count <= 0) {
            cursor.close()
            false
        } else{
            cursor.close()
            true
        }
    }

    fun delete(id_tvshow : String) : Int {
        return database.delete(TABLE_TVSHOW, "$ID_TVSHOW = $id_tvshow", null)
    }
}