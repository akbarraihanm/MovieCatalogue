package com.example.moviecatalogue.tvshows

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

class RvTvShowAdapter (private val context: Context, private var listTvShow : ArrayList<TvShow>)
    : RecyclerView.Adapter<RvTvShowAdapter.TvShowViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, i: Int): TvShowViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rvtvshow_item, parent, false)
        return TvShowViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listTvShow.size
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, i: Int) {
        listTvShow[i].idTvShow?.let { holder.bind(listTvShow[i], context, it) }
    }

    class TvShowViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView) {

        private var ltv : TvShow? = null
        fun bind(ltv : TvShow, con : Context, id : String){
            this.ltv = ltv
            with(itemView){
                with(ltv){
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