package com.example.moviecatalogue.search.searchmovie

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.moviecatalogue.R
import com.example.moviecatalogue.detailmovie.DetailActivity
import com.example.moviecatalogue.model.ListMovie
import kotlinx.android.synthetic.main.rvmovie_item.view.*

class SearchMovieAdapter(private val context: Context, private val listMovie : ArrayList<ListMovie>)
    : RecyclerView.Adapter<SearchMovieAdapter.SearchMovieViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): SearchMovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rvmovie_item, parent, false)
        return SearchMovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listMovie.size
    }

    override fun onBindViewHolder(holder: SearchMovieViewHolder, i: Int) {
        listMovie[i].idMovie?.let { holder.bind(listMovie[i], context, it) }
    }

    class SearchMovieViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        fun bind(listMovie : ListMovie, con : Context, id : String){
            with(itemView){
                with(listMovie){
                    Glide.with(itemView)
                        .load(linkPoster+posterPath)
                        .into(iv_poster)
                    tv_judul.text = title
                }
                itemView.setOnClickListener {
                    val i = Intent(con, DetailActivity::class.java)
                    i.putExtra(DetailActivity.id_movie, id)
                    con.startActivity(i)
                }
            }
        }

    }
}