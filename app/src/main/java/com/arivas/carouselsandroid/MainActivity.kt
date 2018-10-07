package com.arivas.carouselsandroid

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.arivas.carouselsandroid.adapter.ParentAdapter
import com.arivas.carouselsandroid.model.ItemVideoModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException


class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val gson = Gson()
        val json: String = loadData("videoItem.json")
        val itemVideo: ArrayList<ItemVideoModel> = gson.fromJson(json, object : TypeToken<ArrayList<ItemVideoModel>>() {}.type)

        initRecyclerView(itemVideo)
    }

    private fun initRecyclerView(itemsVideo: ArrayList<ItemVideoModel>) {
        recyclerView = rv_parent

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayout.VERTICAL, false)
            adapter = ParentAdapter(itemsVideo)
        }
    }

    fun loadData(file: String): String{
        var content = ""
        try {
            val stream = assets.open(file)
            val size = stream.available()
            val buffer = ByteArray(size)
            stream.read(buffer)
            stream.close()
            content = String(buffer)
        }catch (e: IOException){
            e.printStackTrace()
        }
        return content
    }
}
