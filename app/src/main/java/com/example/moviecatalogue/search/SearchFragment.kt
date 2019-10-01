package com.example.moviecatalogue.search


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.moviecatalogue.R
import com.example.moviecatalogue.search.searchmovie.SearchMovieFragment
import com.example.moviecatalogue.search.searchtvshow.SearchTvshowFragment
import kotlinx.android.synthetic.main.fragment_search.view.*

/**
 * A simple [Fragment] subclass.
 */
class SearchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view =  inflater.inflate(R.layout.fragment_search, container, false)

        val adapter = ViewPagerAdapter(childFragmentManager)
        context?.resources?.getString(R.string.title_movies)?.let {
            adapter.addFragment(SearchMovieFragment(),
                it
            )
        }

        context?.resources?.getString(R.string.title_tvshow)?.let {
            adapter.addFragment(SearchTvshowFragment(),
                it
            )
        }

        view.view_pager_search1.adapter = adapter
        view.tab_layout_search1.setupWithViewPager(view.view_pager_search1)

        return view
    }

    class ViewPagerAdapter(manager : FragmentManager) : FragmentPagerAdapter(manager){

        private val fragmentList : MutableList<Fragment> = ArrayList()
        private val titleList : MutableList<String> = ArrayList()

        override fun getItem(i: Int): Fragment {
            return fragmentList[i]
        }

        override fun getCount(): Int {
            return fragmentList.size
        }

        fun addFragment(fragment: Fragment, title : String){
            fragmentList.add(fragment)
            titleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return titleList[position]
        }
    }

}
