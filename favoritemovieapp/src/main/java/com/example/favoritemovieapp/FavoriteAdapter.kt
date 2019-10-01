package com.example.favoritemovieapp

import android.content.Context
import android.database.Cursor
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CursorAdapter
import android.widget.TextView
import com.example.favoritemovieapp.db.DbContract.Companion.getColumnString
import com.example.favoritemovieapp.db.DbContract.FavMovieColumn.Companion.TITLE_MOVIE

class FavoriteAdapter(context: Context, cursor: Cursor?, autoRequery: Boolean)
    : CursorAdapter(context, cursor, autoRequery){

    private lateinit var tvTitle : TextView

    override fun newView(context: Context?, cursor: Cursor?, viewGroup: ViewGroup?): View {
        val view = LayoutInflater.from(context).inflate(R.layout.favorite_list_item, viewGroup, false)
        return view
    }

    override fun bindView(view: View?, context: Context?, cursor: Cursor?) {
        tvTitle = view!!.findViewById(R.id.tv_fav_item_title)
        tvTitle.text = getColumnString(cursor!!, TITLE_MOVIE)
        Log.d("string", getColumnString(cursor, TITLE_MOVIE))
    }
}

