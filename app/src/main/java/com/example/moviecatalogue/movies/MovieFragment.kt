package com.example.moviecatalogue.movies


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.*
import android.view.View.GONE
import android.view.View.VISIBLE
import com.example.moviecatalogue.R
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
class MovieFragment : Fragment(), MoviesView {

    val STATE_RESULT = "stateresult"

    var MOVIE = arrayListOf<ListMovie>()

    var moviesPresenter: MoviesPresenter? = null
    var movie  : ArrayList<ListMovie>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_movie, container, false)

        view.rvMovie.layoutManager = LinearLayoutManager(context)
        moviesPresenter = context?.let { MoviesPresenter(it, this) }
        if(MOVIE.size == 0){
            context?.resources?.getString(R.string.lang)?.let { moviesPresenter?.getListMovieItems(it) }
        }

        return view
    }

    override fun showMoviesItem(listMovies: ArrayList<ListMovie>) {
        showLoading()
        rvMovie.adapter = context?.let { RvMovieAdapter(it, listMovies) }
        MOVIE = listMovies
        stopLoading()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(STATE_RESULT, movie)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        retainInstance = true
        if(savedInstanceState != null){
            stopLoading()
            rvMovie.adapter = context?.let { RvMovieAdapter(it, MOVIE) }
            movie = savedInstanceState.getParcelableArrayList(STATE_RESULT)
        }
    }

    private fun showLoading(){
        loading_movies.visibility = VISIBLE
    }

    private fun stopLoading(){
        loading_movies.visibility = GONE
    }
}
