package com.example.moviecatalogue.favorites


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.moviecatalogue.R
import com.example.moviecatalogue.favorites.favmovies.FavMoviesFragment
import com.example.moviecatalogue.favorites.favtvshows.FavTvshowsFragment
import kotlinx.android.synthetic.main.fragment_favorites.*
import kotlinx.android.synthetic.main.fragment_favorites.view.*

/**
 * A simple [Fragment] subclass.
 */
class FavoritesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favorites, container, false)

        val adapter = ViewPagerAdapter(childFragmentManager)
        context?.resources?.getString(R.string.title_movies)?.let {
            adapter.addFragment(FavMoviesFragment(),
                it
            )
        }
        context?.resources?.getString(R.string.title_tvshow)?.let {
            adapter.addFragment(FavTvshowsFragment(),
                it
            )
        }
        view.view_pager_fav.adapter = adapter
        view.tab_layout_fav.setupWithViewPager(view.view_pager_fav)

        return view
    }

    class ViewPagerAdapter(manager : FragmentManager) : FragmentPagerAdapter(manager){

        private val fragmentList : MutableList<Fragment> = ArrayList()
        private val titleList : MutableList<String> = ArrayList()

        override fun getItem(position: Int): Fragment {
            return fragmentList[position]
        }

        override fun getCount(): Int {
            return fragmentList.size
        }

        fun addFragment(fragment : Fragment, title : String){
            fragmentList.add(fragment)
            titleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return titleList[position]
        }
    }
}
