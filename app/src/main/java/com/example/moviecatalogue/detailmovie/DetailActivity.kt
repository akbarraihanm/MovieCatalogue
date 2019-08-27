package com.example.moviecatalogue.detailmovie

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.moviecatalogue.R
import com.example.moviecatalogue.favorites.db2.FavMovieHelper
import com.example.moviecatalogue.model.DetailMovie
import com.example.moviecatalogue.model.FavoriteMovie
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(), DetailMovieView {

    companion object{
        var id_movie = "123"
    }

    private var detailMoviePresenter : DetailMoviePresenter? = null
    private var menuItem : Menu? = null
    private var isFavorite : Boolean = false
    private var dbHan : FavMovieHelper? = FavMovieHelper(this)
    private var dataDetailMovie : DetailMovie? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.title = getString(R.string.title_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        id_movie = intent.getStringExtra(id_movie)

        detailMoviePresenter = DetailMoviePresenter(this, this)
        detailMoviePresenter?.getDetailMovieData(id_movie, this.resources.getString(R.string.lang))
        favoriteState(id_movie)
    }

    override fun showDetailMovieItem(detailMovieData: DetailMovie) {

        showLoading()

        dataDetailMovie = detailMovieData

        with(detailMovieData){
            tv_judul.text = titleMovie
            tv_status.text = statusMovie
            val hoho = "$$budgetMovie"
            tv_budget.text = hoho
            tv_detail.text = overviewMovie
            Glide.with(this@DetailActivity)
                .load(linkPoster+posterPath)
                .into(iv_poster)
        }
        stopLoading()
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
                if(isFavorite) {
                    dbHan?.open()
                    removeFromFavorite(id_movie)
                    isFavorite = !isFavorite
                    setFavorite()
                    dbHan?.close()
                }
                else {
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

    private fun favoriteState(idMovie : String){
        dbHan?.open()
        val result = dbHan?.check(idMovie)
        isFavorite = result!!
        dbHan?.close()

    }

    private fun addToFavorite(){
        try {
            dbHan?.open()
            val favMovie = FavoriteMovie()
            favMovie.id_movie = id_movie
            favMovie.title_movie = dataDetailMovie?.titleMovie
            Log.d("cekFav", favMovie.toString())

            dbHan?.insert(favMovie)
            Toast.makeText(this, getString(R.string.addedFav), Toast.LENGTH_SHORT).show()
            dbHan?.close()
        }catch (e : Exception){
            Log.d("cekFav1", e.toString())
        }

    }

    private fun removeFromFavorite(id : String){
        try {
            dbHan?.open()
            dbHan?.delete(id)
            Toast.makeText(this, getString(R.string.removeFavorite), Toast.LENGTH_SHORT).show()
            dbHan?.close()
        }catch (e:Exception){Log.d("cekFav2", e.toString())}
    }

    private fun setFavorite(){
        if(isFavorite){
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_star_black)
        }
        else{
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_star_border_black)
        }
    }

    private fun showLoading(){
        loading_detail_movie.visibility = VISIBLE
    }

    private fun stopLoading(){
        loading_detail_movie.visibility = GONE
    }
}
