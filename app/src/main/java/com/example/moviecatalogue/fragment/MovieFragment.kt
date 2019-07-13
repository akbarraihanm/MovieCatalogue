package com.example.moviecatalogue.fragment


import android.content.res.TypedArray
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.moviecatalogue.R
import com.example.moviecatalogue.adapter.ListViewAdapter
import com.example.moviecatalogue.adapter.RvMovieAdapter
import com.example.moviecatalogue.model.ListMovie
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.android.synthetic.main.fragment_movie.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class MovieFragment : Fragment() {

    private lateinit var listPict : TypedArray
    private lateinit var listName : Array<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_movie, container, false)

        listPict = resources.obtainTypedArray(R.array.image_poster)
        listName = resources.getStringArray(R.array.name_poster)

        view.rvMovie.layoutManager = LinearLayoutManager(context)

        view.rvMovie.adapter = context?.let { RvMovieAdapter(it, populateList()) }

        return view
    }

    private fun populateList(): ArrayList<ListMovie> {
        val list = ArrayList<ListMovie>()

        for(i in listName.indices){
            val listMovie = ListMovie(0,"","","","")
            listMovie.pict = listPict.getResourceId(i, -1)
            listMovie.name = listName[i]
            list.add(listMovie)
        }

        return list
    }


}
