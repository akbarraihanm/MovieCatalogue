package com.example.favoritemovieapp

import android.database.Cursor
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import com.example.favoritemovieapp.db.DbContract.FavMovieColumn.Companion.CONTENT_URI
import kotlinx.android.synthetic.main.activity_main.*

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<Cursor> {

    lateinit var favAdapter : FavoriteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = "Favorite App"

        favAdapter = FavoriteAdapter(this, null, true)
        list_view_movie.adapter = favAdapter

        supportLoaderManager.initLoader(0, null, this)
    }

    override fun onResume() {
        super.onResume()
        supportLoaderManager.restartLoader(0,null,this)
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
        return CursorLoader(this, CONTENT_URI, null, null, null, null)
    }

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        favAdapter.swapCursor(data)
        Log.d("hura123", "$data")
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        favAdapter.swapCursor(null)
    }

    override fun onDestroy() {
        super.onDestroy()
        supportLoaderManager.destroyLoader(0)
    }
}
