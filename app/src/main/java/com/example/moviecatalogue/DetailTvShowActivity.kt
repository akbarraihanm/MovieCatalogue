package com.example.moviecatalogue

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.example.moviecatalogue.model.TvShow
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_detail.iv_poster
import kotlinx.android.synthetic.main.activity_detail.tv_detail
import kotlinx.android.synthetic.main.activity_detail.tv_judul
import kotlinx.android.synthetic.main.activity_detail.tv_status
import kotlinx.android.synthetic.main.activity_detail_tv_show.*

class DetailTvShowActivity : AppCompatActivity() {

    companion object{
        var EXTRA = "ex"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tv_show)
        supportActionBar?.title = getString(R.string.title_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val listTv = intent.getParcelableExtra<TvShow>(EXTRA)

        tv_judul.text = listTv.title
        tv_status.text = listTv.status
        tv_runtime.text = listTv.runtime
        tv_detail.text = listTv.detailTvShow
        Glide.with(this)
            .load(listTv.pict)
            .into(iv_poster)
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
}
