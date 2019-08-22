package com.example.moviecatalogue.detailmovie

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import com.bumptech.glide.Glide
import com.example.moviecatalogue.R
import com.example.moviecatalogue.model.DetailMovie
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(), DetailMovieView {

    companion object{
        var id_movie = "123"
    }

    private var detailMoviePresenter : DetailMoviePresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.title = getString(R.string.title_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        id_movie = intent.getStringExtra(id_movie)

        detailMoviePresenter = DetailMoviePresenter(this, this)
        detailMoviePresenter?.getDetailMovieData(id_movie, this.resources.getString(R.string.lang))

    }

    override fun showDetailMovieItem(detailMovieData: DetailMovie) {

        showLoading()
        with(detailMovieData){
            tv_judul.text = titleMovie
            tv_status.text = statusMovie
            tv_budget.text = "$$budgetMovie"
            tv_detail.text = overviewMovie
            Glide.with(this@DetailActivity)
                .load(linkPoster+posterPath)
                .into(iv_poster)
        }
        stopLoading()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId){
            android.R.id.home ->{
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showLoading(){
        loading_detail_movie.visibility = VISIBLE
    }

    private fun stopLoading(){
        loading_detail_movie.visibility = GONE
    }
}
