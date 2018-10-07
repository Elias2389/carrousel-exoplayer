package com.arivas.carouselsandroid.adapter

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.arivas.carouselsandroid.PlayerActivity
import com.arivas.carouselsandroid.R
import com.arivas.carouselsandroid.model.ItemModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.child_recycler_thumb.view.*
import org.jetbrains.anko.toast

class ChildThumbAdapter(private val item: ArrayList<ItemModel>): RecyclerView.Adapter<ChildThumbAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.child_recycler_thumb,parent,false))
    }

    override fun getItemCount(): Int {
        return item.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = item[position]
        holder.textView.text = item.title
        Picasso.get().load(item.url).into(holder.imageView)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.mContext, PlayerActivity::class.java)

            if(item.video == null){
                holder.mContext.toast(R.string.no_available_video)
            }else{
                intent.putExtra("title", item.title)
                intent.putExtra("url", item.url)
                intent.putExtra("video", item.video)

                holder.mContext.startActivity(intent)
            }
        }
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.child_textView_thumb
        val imageView: ImageView = itemView.child_imageView_thumb
        val mContext = itemView.context
    }
}