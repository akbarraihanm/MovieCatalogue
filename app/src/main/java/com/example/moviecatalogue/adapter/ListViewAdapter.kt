package com.example.moviecatalogue.adapter

import android.content.Context
import android.content.Intent
import android.content.res.TypedArray
import android.os.Build
import android.support.annotation.RequiresApi
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.moviecatalogue.DetailActivity
import com.example.moviecatalogue.R
import com.example.moviecatalogue.model.ListMovie
import kotlinx.android.synthetic.main.lvmovie_catalogue.view.*

class ListViewAdapter(private val context: Context?, private val listMovie: ArrayList<ListMovie>) : BaseAdapter(){

    private lateinit var listPict : TypedArray
    private lateinit var listName : Array<String>
    private lateinit var listDetail : Array<String>
    private lateinit var listStatus : Array<String>
    private lateinit var listBudget : Array<String>

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        if (context != null) {
            listPict = context.resources.obtainTypedArray(R.array.image_poster)
            listName = context.resources.getStringArray(R.array.name_poster)
            listDetail = context.resources.getStringArray(R.array.detail_poster)
            listStatus = context.resources.getStringArray(R.array.status)
            listBudget = context.resources.getStringArray(R.array.budget)
        }

        val holder : ViewHolder
        var view = convertView
        if(view == null){
            holder = ViewHolder()
            val inflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.lvmovie_catalogue, null, true)

            holder.ivPoster = view.iv_poster
            holder.tvJudul = view.tv_judul

            view.tag = holder
        }
        else{
            holder = view.tag as ViewHolder
        }

        listMovie[position].pict?.let { holder.ivPoster?.setImageResource(it) }
        holder.tvJudul!!.text = listMovie[position].name

        holder.ivPoster!!.setOnClickListener {
            val listJudul = ListMovie(0,"","","","")
            listJudul.name = listName[position]
            listJudul.pict = listPict.getResourceId(position,position)
            listJudul.detailPoster = listDetail[position]
            listJudul.status = listStatus[position]
            listJudul.budget = listBudget[position]

            val i = Intent(context, DetailActivity::class.java)
//            i.putExtra(DetailActivity.EXTRA_PICT, listJudul)

            if (context != null) {
                context.startActivity(i)
                Toast.makeText(context, "Item dipilih : "+listMovie[position].name, Toast.LENGTH_SHORT).show()
            }

        }

        return view!!

    }

    override fun getItem(position: Int): Any {
        return listMovie[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return listMovie.size
    }

    private inner class ViewHolder{
        var ivPoster : ImageView? = null
        var tvJudul : TextView? = null
    }

}