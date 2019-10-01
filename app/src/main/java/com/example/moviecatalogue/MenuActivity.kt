package com.example.moviecatalogue

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.moviecatalogue.favorites.FavoritesFragment
import com.example.moviecatalogue.movies.MovieFragment
import com.example.moviecatalogue.reminders.RemindersActivity
import com.example.moviecatalogue.search.SearchFragment
import com.example.moviecatalogue.tvshows.TvShowFragment

class MenuActivity : AppCompatActivity() {

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
            R.id.navigation_favorite -> {
                fragment = FavoritesFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container_layout, fragment, fragment.javaClass.simpleName)
                    .commit()
                supportActionBar?.title = this.resources.getString(R.string.favorites)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_search -> {
                fragment = SearchFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container_layout, fragment, fragment.javaClass.simpleName)
                    .commit()
                supportActionBar?.title = this.resources.getString(R.string.search)
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
                val intent = Intent(Settings.ACTION_LOCALE_SETTINGS)
                startActivity(intent)
                true
            }

            R.id.action_change_reminders -> {
                val intent = Intent(this, RemindersActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
