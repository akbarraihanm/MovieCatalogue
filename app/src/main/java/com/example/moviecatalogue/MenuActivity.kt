package com.example.moviecatalogue

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.os.PersistableBundle
import android.provider.Settings
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import android.widget.TextView
import com.example.moviecatalogue.movies.MovieFragment
import com.example.moviecatalogue.tvshows.TvShowFragment
import kotlinx.android.synthetic.main.fragment_movie.*

class MenuActivity : AppCompatActivity() {

    private lateinit var textMessage: TextView
    private lateinit var fragment : Fragment
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_movie -> {
//                textMessage.setText(R.string.title_home)
                fragment = MovieFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container_layout, fragment, fragment.javaClass.simpleName)
                    .commit()
                supportActionBar?.title = getString(R.string.title_movies)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_tvshow -> {
//                textMessage.setText(R.string.title_dashboard)
                fragment = TvShowFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container_layout, fragment, fragment.javaClass.simpleName)
                    .commit()
                supportActionBar?.title = getString(R.string.title_tvshow)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

//        textMessage = findViewById(R.id.message)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        if(savedInstanceState == null){
            supportActionBar?.title = getString(R.string.title_movies)
            navView.selectedItemId = R.id.navigation_movie
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.language, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId){
            R.id.action_change_settings ->{
                val inte = Intent(Settings.ACTION_LOCALE_SETTINGS)
                startActivity(inte)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
