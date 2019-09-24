package com.example.moviecatalogue.favorites.favmovies


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import com.example.moviecatalogue.R
import com.example.moviecatalogue.favorites.db2.FavMovieHelper
import com.example.moviecatalogue.model.FavoriteMovie
import kotlinx.android.synthetic.main.fragment_fav_movies.view.*

/**
 * A simple [Fragment] subclass.
 */
class FavMoviesFragment : Fragment() {

    private var listFavMovie : ArrayList<FavoriteMovie> = arrayListOf()
    private lateinit var dbHan : FavMovieHelper
    private lateinit var okView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        okView = inflater.inflate(R.layout.fragment_fav_movies, container, false)

        dbHan = FavMovieHelper(okView.context)

        okView.tv_no_data.visibility = GONE

        return okView
    }

    override fun onResume() {
        super.onResume()
        okView.rvFavMovie.adapter = null

        dbHan.open()
        listFavMovie = dbHan.query()
        Log.d("listData","$listFavMovie")
        okView.rvFavMovie.layoutManager = LinearLayoutManager(context)

        if(listFavMovie.isNotEmpty()) {
            okView.rvFavMovie.adapter = context?.let { FavMovieAdapter(it, listFavMovie) }
            dbHan.close()
        }
        else{
            okView.tv_no_data?.visibility = VISIBLE
            dbHan.close()
        }
    }
}
