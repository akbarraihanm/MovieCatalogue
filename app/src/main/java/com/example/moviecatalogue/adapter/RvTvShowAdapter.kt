package com.example.moviecatalogue.adapter

import android.content.Context
import android.content.Intent
import android.content.res.TypedArray
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.moviecatalogue.DetailTvShowActivity
import com.example.moviecatalogue.R
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
        holder.bind(listTvShow[i], context, i)
    }

    class TvShowViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView) {

        private lateinit var listPict : TypedArray
        private lateinit var listTitle : Array<String>
        private lateinit var listDetail : Array<String>
        private lateinit var listStatus : Array<String>
        private lateinit var listRuntime : Array<String>

        private var ltv : TvShow? = null
        fun bind(ltv : TvShow, con : Context, id : Int){
            this.ltv = ltv
            with(itemView){
                with(ltv){
                    Glide.with(itemView)
                        .load(pict)
                        .into(iv_poster)
                    tv_judul.text = title
                }
                iv_poster.setOnClickListener {
                    listPict = resources.obtainTypedArray(R.array.image_tvshow)
                    listTitle = resources.getStringArray(R.array.title_tvshow)
                    listDetail = resources.getStringArray(R.array.detail_tvshow)
                    listStatus = resources.getStringArray(R.array.status_tvshow)
                    listRuntime = resources.getStringArray(R.array.runtime_tvshow)
                    ltv.pict = listPict.getResourceId(id,id)
                    ltv.title = listTitle[id]
                    ltv.detailTvShow = listDetail[id]
                    ltv.status = listStatus[id]
                    ltv.runtime = listRuntime[id]

                    val i = Intent(con, DetailTvShowActivity::class.java)
                    i.putExtra(DetailTvShowActivity.EXTRA, ltv)
                    con.startActivity(i)
                }
            }
        }
    }

}