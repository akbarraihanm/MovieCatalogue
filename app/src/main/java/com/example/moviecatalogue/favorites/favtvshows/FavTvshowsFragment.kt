package com.example.moviecatalogue.favorites.favtvshows


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
import com.example.moviecatalogue.favorites.db2.FavTvShowHelper
import com.example.moviecatalogue.model.FavoriteTvShow
import kotlinx.android.synthetic.main.fragment_fav_tvshows.view.*

/**
 * A simple [Fragment] subclass.
 */
class FavTvshowsFragment : Fragment() {

    private lateinit var tvShowView : View
    private lateinit var dbHan : FavTvShowHelper
    private var listFavTvShow : ArrayList<FavoriteTvShow> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        tvShowView = inflater.inflate(R.layout.fragment_fav_tvshows, container, false)

        tvShowView.tv_no_data_favtvshow.visibility = GONE

        dbHan = FavTvShowHelper(tvShowView.context)

        return tvShowView
    }

    override fun onResume() {
        super.onResume()
        tvShowView.rvFavTvShow.adapter = null

        dbHan.open()
        listFavTvShow = dbHan.query()
        Log.d("listData","$listFavTvShow")
        tvShowView.rvFavTvShow.layoutManager = LinearLayoutManager(tvShowView.context)

        if(listFavTvShow.isNotEmpty()){
            tvShowView.rvFavTvShow.adapter = FavTvShowAdapter(tvShowView.context, listFavTvShow)
            dbHan.close()
        }
        else{
            tvShowView.tv_no_data_favtvshow.visibility = VISIBLE
            dbHan.close()
        }
    }
}
