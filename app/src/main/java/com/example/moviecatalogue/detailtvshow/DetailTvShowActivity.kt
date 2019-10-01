package com.example.moviecatalogue.detailtvshow

import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.moviecatalogue.R
import com.example.moviecatalogue.favorites.db2.FavTvShowHelper
import com.example.moviecatalogue.model.DetailTvShow
import com.example.moviecatalogue.model.FavoriteTvShow
import kotlinx.android.synthetic.main.activity_detail_tv_show.*

class DetailTvShowActivity : AppCompatActivity(), DetailTvShowView {

    companion object{
        var id_tvshow = "123"
    }

    private var detailTvShowPresenter : DetailTvShowPresenter? = null
    private var menuItem : Menu? = null
    private var isFavorite : Boolean = false
    private var dbHan : FavTvShowHelper? = FavTvShowHelper(this)
    private var detailTvShow : DetailTvShow? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tv_show)
        supportActionBar?.title = getString(R.string.title_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        id_tvshow = intent.getStringExtra(id_tvshow)

        detailTvShowPresenter = DetailTvShowPresenter(this, this)
        detailTvShowPresenter?.getDataDetailTvShow(id_tvshow, this.resources.getString(R.string.lang))
        favoriteState(id_tvshow)
    }

    override fun showDataDetailTvShow(dataDetailTvShow: DetailTvShow) {
        showLoading()

        detailTvShow = dataDetailTvShow

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

    private fun favoriteState(idTvshow: String?) {
        dbHan?.open()
        val result = idTvshow?.let { dbHan?.check(it) }
        isFavorite = result!!
        dbHan?.close()
    }

    private fun setFavorite() {
        if(isFavorite){
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_star_black)
        }
        else{
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_star_border_black)
        }
    }

    private fun addToFavorite() {
        try {
            dbHan?.open()
            val favTvShow = FavoriteTvShow()
            favTvShow.id_tvshow = id_tvshow
            favTvShow.title_tvshow = detailTvShow?.nameTvShow
            favTvShow.link_poster = "https://image.tmdb.org/t/p/w185"
            favTvShow.poster_path = detailTvShow?.posterPath
            Log.d("cekFav1", favTvShow.toString())
            dbHan?.insert(favTvShow)
            Toast.makeText(this, getString(R.string.addedFav), Toast.LENGTH_SHORT).show()
            dbHan?.close()
        }catch (e:Exception){
            Log.d("cekFav1", e.toString())
        }
    }

    private fun removeFromFavorite(idTvshow: String) {
        try {
            dbHan?.open()
            dbHan?.delete(idTvshow)
            Toast.makeText(this, getString(R.string.removeFavorite), Toast.LENGTH_SHORT).show()
            dbHan?.close()
        }catch (e:Exception){
            Log.d("cekFav2", e.toString())}
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.favorite, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId){
            android.R.id.home ->{
                finish()
                true
            }
            R.id.action_favorite -> {
                if(isFavorite){
                    dbHan?.open()
                    removeFromFavorite(id_tvshow)
                    isFavorite = !isFavorite
                    setFavorite()
                    dbHan?.close()
                }
                else{
                    dbHan?.open()
                    addToFavorite()
                    isFavorite = !isFavorite
                    setFavorite()
                    dbHan?.close()
                }
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
