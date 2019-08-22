package com.example.moviecatalogue.detailtvshow

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import com.bumptech.glide.Glide
import com.example.moviecatalogue.R
import com.example.moviecatalogue.model.DetailTvShow
import kotlinx.android.synthetic.main.activity_detail_tv_show.*

class DetailTvShowActivity : AppCompatActivity(), DetailTvShowView {

    companion object{
        var id_tvshow = "123"
    }

    var detailTvShowPresenter : DetailTvShowPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tv_show)
        supportActionBar?.title = getString(R.string.title_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        id_tvshow = intent.getStringExtra(id_tvshow)

        detailTvShowPresenter = DetailTvShowPresenter(this, this)
        detailTvShowPresenter?.getDataDetailTvShow(id_tvshow, this.resources.getString(R.string.lang))

    }

    override fun showDataDetailTvShow(dataDetailTvShow: DetailTvShow) {
        showLoading()

        with(dataDetailTvShow){
            tv_judul.text = nameTvShow
            tv_status.text = statusTvShow
            tv_runtime.text = runTime[0]
            tv_detail.text = overview
            Glide.with(this@DetailTvShowActivity)
                .load("https://image.tmdb.org/t/p/w185$posterPath")
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
        loading_detail_tvshow.visibility = View.VISIBLE
    }

    private fun stopLoading(){
        loading_detail_tvshow.visibility = View.GONE
    }
}
