package com.example.project_innogeeks.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.project_innogeeks.Model.StoreDataType
import com.example.project_innogeeks.R


class MyListAdapter(private val context: Context,private val itemList: List<StoreDataType>) : RecyclerView.Adapter<MyListAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.each_block, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = itemList[position]

        holder.tname.text=currentItem.name
        holder.phone.text=currentItem.phone
        Glide.with(holder.image)
            .load(currentItem.Imageurl)
            .into(holder.image)


    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tname=itemView.findViewById<TextView>(R.id.tname)
        val image=itemView.findViewById<ImageView>(R.id.timage)
        val phone=itemView.findViewById<TextView>(R.id.tphone)
        val connectbtn=itemView.findViewById<Button>(R.id.connect)
    }
}