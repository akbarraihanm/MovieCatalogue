package com.example.moviecatalogue

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.example.moviecatalogue.model.ListMovie
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object{
        var EXTRA_BARU ="0123"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.title = getString(R.string.title_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val listJudul : ListMovie = intent.getParcelableExtra(EXTRA_BARU)

        tv_judul.text = listJudul.name
        tv_status.text = listJudul.status
        tv_budget.text = listJudul.budget
        tv_detail.text = listJudul.detailPoster
        Glide.with(this)
            .load(listJudul.pict)
            .into(iv_poster)
//        iv_poster.setImageResource(listJudul.pict!!)
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
