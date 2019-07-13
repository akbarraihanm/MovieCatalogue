package com.example.moviecatalogue.adapter

import android.content.Context
import android.content.Intent
import android.content.res.TypedArray
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.moviecatalogue.DetailActivity
import com.example.moviecatalogue.R
import com.example.moviecatalogue.model.ListMovie
import kotlinx.android.synthetic.main.fragment_movie.view.*
import kotlinx.android.synthetic.main.rvmovie_item.view.*

class RvMovieAdapter (private val context: Context, private val listMovie : ArrayList<ListMovie>)
    : RecyclerView.Adapter<RvMovieAdapter.MovieViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, i: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rvmovie_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listMovie.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, i: Int) {
        holder.bind(listMovie[i], context, i)
    }

    class MovieViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView) {

        private lateinit var listPict : TypedArray
        private lateinit var listName : Array<String>
        private lateinit var listDetail : Array<String>
        private lateinit var listStatus : Array<String>
        private lateinit var listBudget : Array<String>

        private var lm : ListMovie? = null
        fun bind(lm : ListMovie, con : Context, id : Int){
            this.lm = lm
            with(itemView){
                with(lm){
                    Glide.with(itemView)
                        .load(pict)
                        .into(iv_poster)
                    tv_judul.text = name
                }
                iv_poster.setOnClickListener {
                    listPict = resources.obtainTypedArray(R.array.image_poster)
                    listName = resources.getStringArray(R.array.name_poster)
                    listDetail = resources.getStringArray(R.array.detail_poster)
                    listStatus = resources.getStringArray(R.array.status)
                    listBudget = resources.getStringArray(R.array.budget)
                    val list = ListMovie(0, "", "", "", "")
                    list.pict = listPict.getResourceId(id, id)
                    list.name = listName[id]
                    list.budget = listBudget[id]
                    list.status = listStatus[id]
                    list.detailPoster = listDetail[id]
                    val i = Intent(con, DetailActivity::class.java)
                    i.putExtra(DetailActivity.EXTRA_BARU, list)
                    con.startActivity(i)
                }
            }
        }
    }

}