package com.example.moviecatalogue.favorites.favtvshows

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.moviecatalogue.R
import com.example.moviecatalogue.detailtvshow.DetailTvShowActivity
import com.example.moviecatalogue.model.FavoriteTvShow
import kotlinx.android.synthetic.main.rvfavtvshow_item.view.*

class FavTvShowAdapter (private val context: Context, private val listFavTvShow : ArrayList<FavoriteTvShow>)
    : RecyclerView.Adapter<FavTvShowAdapter.FavTvShowViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): FavTvShowViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rvfavtvshow_item, parent, false)
        return FavTvShowViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listFavTvShow.size
    }

    override fun onBindViewHolder(holder: FavTvShowViewHolder, i: Int) {
        listFavTvShow[i].id_tvshow?.let { holder.bind(listFavTvShow[i], context, it) }
    }

    class FavTvShowViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bind(lft : FavoriteTvShow, con : Context, id_tvshow : String){
            with(itemView){
                with(lft){
                    tv_favtvshow_title.text = title_tvshow
                }
                cv_list_favtvshow.setOnClickListener {
                    val intent = Intent(con, DetailTvShowActivity::class.java)
                    intent.putExtra(DetailTvShowActivity.id_tvshow, id_tvshow)
                    con.startActivity(intent)
                }
            }
        }
    }
}