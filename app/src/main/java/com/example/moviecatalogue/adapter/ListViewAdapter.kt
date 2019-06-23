package com.example.moviecatalogue.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.moviecatalogue.R
import com.example.moviecatalogue.model.ListMovie

class ListViewAdapter (private val context: Context, private val listMovie : ArrayList<ListMovie>): BaseAdapter(){
    override fun getView(i: Int, convertView: View?, parent: ViewGroup?): View {
        val holder : ViewHolder
        var view = convertView

        if (convertView == null){
            holder = ViewHolder()

            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.lvmovie_catalogue,null, true)

            holder.ivPoster = view.findViewById(R.id.ivPoster)
            holder.tvJudul = view.findViewById(R.id.tvJudul)

            view.tag = holder
        }
        else{
            holder = view!!.tag as ViewHolder
        }

        return view!!
    }

    override fun getItem(i: Int): Any {
        return listMovie[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getCount(): Int {
        return listMovie.size
    }

    private inner class ViewHolder{
        var ivPoster : ImageView? = null
        var tvJudul : TextView? = null
    }

}