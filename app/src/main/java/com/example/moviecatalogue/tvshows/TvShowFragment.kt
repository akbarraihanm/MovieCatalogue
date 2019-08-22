package com.example.moviecatalogue.tvshows


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.moviecatalogue.R
import com.example.moviecatalogue.model.TvShow
import kotlinx.android.synthetic.main.fragment_tv_show.*
import kotlinx.android.synthetic.main.fragment_tv_show.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class TvShowFragment : Fragment(), TvShowView {

    var tvShowsPresenter: TvShowsPresenter? = null

    val STATE_RESULT = "hohoho"

    var TVSHOW = arrayListOf<TvShow>()

    var tvShow : ArrayList<TvShow>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_tv_show, container, false)

        view.rvTvshow.layoutManager = LinearLayoutManager(context)
        tvShowsPresenter = context?.let { TvShowsPresenter(it, this) }
        if(TVSHOW.size == 0){
            context?.resources?.getString(R.string.lang)?.let { tvShowsPresenter?.getTvShowItems(it) }
        }
        return view
    }

    override fun showListTvShows(listTvShow: ArrayList<TvShow>) {
        showLoading()
        rvTvshow.adapter = context?.let { RvTvShowAdapter(it, listTvShow) }
        TVSHOW = listTvShow
        stopLoading()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(STATE_RESULT, tvShow)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        retainInstance = true
        if(savedInstanceState != null){
            stopLoading()
            rvTvshow.adapter = context?.let { RvTvShowAdapter(it, TVSHOW) }
            tvShow = savedInstanceState.getParcelableArrayList(STATE_RESULT)
        }
    }

    private fun showLoading(){
        loading_tvshow.visibility = View.VISIBLE
    }

    private fun stopLoading(){
        loading_tvshow.visibility = View.GONE
    }

}
