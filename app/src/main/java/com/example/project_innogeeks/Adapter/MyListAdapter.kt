package com.example.project_innogeeks.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.project_innogeeks.ChatActivity
import com.example.project_innogeeks.Model.StoreDataType
import com.example.project_innogeeks.R
import com.example.project_innogeeks.ShowActivity


class MyListAdapter(private val context: Context,private var itemList: List<StoreDataType>) : RecyclerView.Adapter<MyListAdapter.ItemViewHolder>() {
    private var originalItemList: List<StoreDataType> = itemList
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.each_block, parent, false)
        return ItemViewHolder(view)
    }




    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = itemList[position]

        holder.tname.text=currentItem.name
        holder.phone.text=currentItem.phone
        holder.address.text=currentItem.addrress
        holder.charge.text=currentItem.about
        Glide.with(holder.image)
            .load(currentItem.Imageurl)
            .into(holder.image)
        holder.connectbtn.setOnClickListener{
            val intent=Intent(context, ChatActivity::class.java)
            intent.putExtra("Username", currentItem.name)
            intent.putExtra("RecieverUid",currentItem.uid)
            context.startActivity(intent)
        }
        holder.showbtn.setOnClickListener {
            val intent=Intent(context,ShowActivity::class.java)
            intent.putExtra("name",currentItem.name)
            intent.putExtra("address",currentItem.addrress)
            intent.putExtra("charge",currentItem.about)
            intent.putExtra("image",currentItem.Imageurl)
            intent.putExtra("phone",currentItem.phone)
            intent.putExtra("uid",currentItem.uid)
            context.startActivity(intent)
        }


    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun filter(query: String) {
        itemList = if (query.isBlank()) {
            originalItemList // If the query is empty, show all items
        } else {
            originalItemList.filter { item ->
                item.addrress.contains(query, ignoreCase = true) // Filter by address
            }
        }
        notifyDataSetChanged()
    }


    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tname=itemView.findViewById<TextView>(R.id.tname)
        val image=itemView.findViewById<ImageView>(R.id.timage)
        val phone=itemView.findViewById<TextView>(R.id.tphone)
        val connectbtn=itemView.findViewById<ImageView>(R.id.connect)
        var address=itemView.findViewById<TextView>(R.id.address1)
        val charge=itemView.findViewById<TextView>(R.id.Charge)
        val showbtn=itemView.findViewById<Button>(R.id.showdetail)
    }
}