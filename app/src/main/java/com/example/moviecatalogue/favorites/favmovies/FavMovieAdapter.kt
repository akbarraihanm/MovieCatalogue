package com.example.moviecatalogue.favorites.favmovies

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.moviecatalogue.R
import com.example.moviecatalogue.detailmovie.DetailActivity
import com.example.moviecatalogue.model.FavoriteMovie
import kotlinx.android.synthetic.main.rvfavmovie_item.view.*

class FavMovieAdapter(private val context: Context, private val listFavMovie : ArrayList<FavoriteMovie>)
    : RecyclerView.Adapter<FavMovieAdapter.FavMovieViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): FavMovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rvfavmovie_item, parent, false)
        return FavMovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listFavMovie.size
    }

    override fun onBindViewHolder(holder: FavMovieViewHolder, i: Int) {
        listFavMovie[i].id_movie?.let { holder.bind(listFavMovie[i], context, it) }
    }

    class FavMovieViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bind(lfm : FavoriteMovie, con : Context, id : String){
            with(itemView){
                with(lfm){
                    tv_favmovie_title.text = title_movie
                }
                cv_list_favmovie.setOnClickListener {
                    val i = Intent(con, DetailActivity::class.java)
                    i.putExtra(DetailActivity.id_movie, id)
                    con.startActivity(i)
                }
            }
        }
    }
}