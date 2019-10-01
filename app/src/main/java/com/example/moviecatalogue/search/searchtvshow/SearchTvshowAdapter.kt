package com.example.moviecatalogue.search.searchtvshow

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.moviecatalogue.R
import com.example.moviecatalogue.detailtvshow.DetailTvShowActivity
import com.example.moviecatalogue.model.TvShow
import kotlinx.android.synthetic.main.rvtvshow_item.view.*

class SearchTvshowAdapter(private val context: Context, private val listTvShow : ArrayList<TvShow>)
    : RecyclerView.Adapter<SearchTvshowAdapter.SearchTvshowViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): SearchTvshowViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rvtvshow_item, parent, false)
        return SearchTvshowViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listTvShow.size
    }

    override fun onBindViewHolder(holder: SearchTvshowViewHolder, i: Int) {
        listTvShow[i].idTvShow?.let { holder.bind(listTvShow[i], context, it) }
    }

    class SearchTvshowViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        fun bind(listTvShow : TvShow, con : Context, id : String){
            with(itemView){
                with(listTvShow){
                    Glide.with(itemView)
                        .load(linkPoster+posterPath)
                        .into(iv_poster)
                    tv_judul.text = name
                }
                itemView.setOnClickListener {
                    val i = Intent(con, DetailTvShowActivity::class.java)
                    i.putExtra(DetailTvShowActivity.id_tvshow, id)
                    con.startActivity(i)
                }
            }
        }
    }
}