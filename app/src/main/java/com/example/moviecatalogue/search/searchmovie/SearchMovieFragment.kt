package com.example.moviecatalogue.search.searchmovie


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast

import com.example.moviecatalogue.R
import com.example.moviecatalogue.model.ListMovie
import kotlinx.android.synthetic.main.fragment_search_movie.*
import kotlinx.android.synthetic.main.fragment_search_movie.view.*

/**
 * A simple [Fragment] subclass.
 */
class SearchMovieFragment : Fragment(), SearchMovieView {

    private var searchMoviePresenter: SearchMoviePresenter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_search_movie, container, false)

        view.rv_search_movies.layoutManager =
            LinearLayoutManager(context)
        searchMoviePresenter = SearchMoviePresenter(this)

        view.search_view_movies.setOnQueryTextListener(object : SearchView.OnQueryTextListener{

            override fun onQueryTextSubmit(query: String?): Boolean {
                if(query != null){
                    Log.d("isiquery",query)
                    context?.resources?.getString(R.string.lang)?.let {
                        searchMoviePresenter?.getMovieItem(
                            it, query
                        )
                    }
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText != null){
                    Log.d("isiquerybaru",newText)
                    context?.resources?.getString(R.string.lang)?.let {
                        searchMoviePresenter?.getMovieItem(
                            it, newText
                        )
                    }
                }
                return true
            }

        })

        return view
    }

    override fun showSearchMovieData(listMovie: ArrayList<ListMovie>) {
        if(listMovie.isEmpty()){
            Toast.makeText(context, "Data tidak ada", Toast.LENGTH_SHORT).show()
        }
        else {
            Log.d("isilistmovie","$listMovie")
            rv_search_movies.adapter = context?.let { SearchMovieAdapter(it, listMovie) }
        }
    }

    override fun showLoading() {
        pb_load_search_movie.visibility = VISIBLE
    }

    override fun stopLoading() {
        pb_load_search_movie.visibility = GONE
    }

    override fun toastNoConnection() {
        Toast.makeText(context, "Tidak ada koneksi", Toast.LENGTH_SHORT).show()
    }

}
