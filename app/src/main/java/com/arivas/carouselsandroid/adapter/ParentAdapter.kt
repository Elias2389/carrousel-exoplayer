package com.arivas.carouselsandroid.adapter

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.arivas.carouselsandroid.R
import com.arivas.carouselsandroid.model.ItemVideoModel
import kotlinx.android.synthetic.main.parent_recycler.view.*

class ParentAdapter(private val itemVideo: ArrayList<ItemVideoModel>): RecyclerView.Adapter<ParentAdapter.ViewHolder>() {
    private val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.parent_recycler, parent, false))
    }

    override fun getItemCount(): Int {
        return itemVideo.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val parent = itemVideo[position]
        holder.textView.text = parent.title
        val childLayoutManager = LinearLayoutManager(holder.recyclerView.context, LinearLayout.HORIZONTAL,false)

        childLayoutManager.initialPrefetchItemCount = 4

        holder.recyclerView.apply {
            layoutManager = childLayoutManager
            when(parent.type){
                "thumb" -> {
                    adapter = ChildThumbAdapter(parent.items)
                }
                "poster" -> {
                    adapter = ChildPosterAdapter(parent.items)
                }
                else -> {}
            }
            recycledViewPool = viewPool
        }
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val recyclerView: RecyclerView = itemView.rv_child
        val textView: TextView = itemView.textView
    }
}