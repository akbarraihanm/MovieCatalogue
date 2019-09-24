package com.example.moviecatalogue.search.searchtvshow


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast

import com.example.moviecatalogue.R
import com.example.moviecatalogue.model.TvShow
import kotlinx.android.synthetic.main.fragment_search_tvshow.*
import kotlinx.android.synthetic.main.fragment_search_tvshow.view.*

/**
 * A simple [Fragment] subclass.
 */
class SearchTvshowFragment : Fragment(), SearchTvshowView {

    private var searchTvShowPresenter : SearchTvshowPresenter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_search_tvshow, container, false)

        view.rv_search_tvshow.layoutManager = LinearLayoutManager(context)
        searchTvShowPresenter = SearchTvshowPresenter(this)

        view.search_view_tvshow.setOnQueryTextListener(object : SearchView.OnQueryTextListener{

            override fun onQueryTextSubmit(query: String?): Boolean {
                if(query != null){
                    context?.resources?.getString(R.string.lang)?.let {
                        searchTvShowPresenter?.getSearchTvShow(
                            it, query)
                    }
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText != null){
                    context?.resources?.getString(R.string.lang)?.let {
                        searchTvShowPresenter?.getSearchTvShow(
                            it, newText)
                    }
                }
                return true
            }

        })

        return view
    }

    override fun showTvShowData(listTvShow: ArrayList<TvShow>) {
        if(listTvShow.isEmpty()){
            Toast.makeText(context, "Data tidak ada", Toast.LENGTH_SHORT).show()
        }
        else{
            rv_search_tvshow.adapter = context?.let { SearchTvshowAdapter(it, listTvShow) }
        }
    }

    override fun showLoading() {
        pb_load_search_tvshow.visibility = VISIBLE
    }

    override fun stopLoading() {
        pb_load_search_tvshow.visibility = GONE
    }

    override fun toastNoConnection() {
        Toast.makeText(context, "Tidak ada koneksi", Toast.LENGTH_SHORT).show()
    }

}
