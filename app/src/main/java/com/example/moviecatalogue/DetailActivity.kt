package com.example.moviecatalogue

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.moviecatalogue.model.ListMovie
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object{
        var EXTRA_JUDUL = "extra movie"
        var EXTRA_STATUS = "extra status"
        var EXTRA_BUDGET = "extra budget"
        var EXTRA_DETAIL = "extra detail"
        var EXTRA_PICT = "0"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val listJudul : ListMovie = intent.getParcelableExtra(EXTRA_PICT)

        tv_judul.text = listJudul.name
        tv_status.text = listJudul.status
        tv_budget.text = listJudul.budget
        tv_detail.text = listJudul.detailPoster
        iv_poster.setImageResource(listJudul.pict!!)
    }
}
