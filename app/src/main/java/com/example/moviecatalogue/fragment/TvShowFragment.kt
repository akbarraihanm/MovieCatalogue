package com.example.moviecatalogue.fragment


import android.content.res.TypedArray
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.moviecatalogue.R
import com.example.moviecatalogue.adapter.RvTvShowAdapter
import com.example.moviecatalogue.model.ListMovie
import com.example.moviecatalogue.model.TvShow
import kotlinx.android.synthetic.main.fragment_movie.view.*
import kotlinx.android.synthetic.main.fragment_tv_show.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class TvShowFragment : Fragment() {

    private lateinit var listPict : TypedArray
    private lateinit var listName : Array<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_tv_show, container, false)

        listPict = resources.obtainTypedArray(R.array.image_tvshow)
        listName = resources.getStringArray(R.array.title_tvshow)

        view.rvTvshow.layoutManager = LinearLayoutManager(context)
        view.rvTvshow.adapter = context?.let { RvTvShowAdapter(it, populateList()) }

        return view
    }

    private fun populateList(): ArrayList<TvShow> {
        val list = ArrayList<TvShow>()

        for(i in listName.indices){
            val listMovie = TvShow(0,"","","","")
            listMovie.pict = listPict.getResourceId(i, -1)
            listMovie.title = listName[i]
            list.add(listMovie)
        }

        return list
    }


}
