package com.example.moviecatalogue

import android.content.res.TypedArray
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.moviecatalogue.adapter.ListViewAdapter
import com.example.moviecatalogue.model.ListMovie
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var listAdapter : ListViewAdapter

    private lateinit var listPict : TypedArray
    private lateinit var listName : Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listPict = resources.obtainTypedArray(R.array.image_poster)
        listName = resources.getStringArray(R.array.name_poster)

        listAdapter = ListViewAdapter(this, populateList())
        this.lv_list.adapter = listAdapter
    }

    private fun populateList(): ArrayList<ListMovie> {
        val list = ArrayList<ListMovie>()

        for(i in listName.indices){
            val listMovie = ListMovie(0,"","","","")
            listMovie.pict = listPict.getResourceId(i, -1)
            listMovie.name = listName[i]
            list.add(listMovie)
        }

        return list
    }
}
