package com.example.project_innogeeks.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project_innogeeks.MainActivity2
import com.example.project_innogeeks.Model.ProfeesionType
import com.example.project_innogeeks.Model.StoreDataType
import com.example.project_innogeeks.R


class MyMainAdapter(private val context: Context,private var itemList: List<ProfeesionType>) : RecyclerView.Adapter<MyMainAdapter.ItemViewHolder>() {
    private var originalItemList: List<ProfeesionType> = itemList
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_detail_list_item, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = itemList[position]
         holder.jobtittle.text=currentItem.name
        holder.image.setImageResource(currentItem.image)

        holder.itemView.setOnClickListener {
            val intent= Intent(context,MainActivity2::class.java)
            intent.putExtra("name",currentItem.name)
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
                item.name.contains(query, ignoreCase = true) // Filter by address
            }
        }
        notifyDataSetChanged()
    }


    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
          val jobtittle=itemView.findViewById<TextView>(R.id.JobTitle)
        val image=itemView.findViewById<ImageView>(R.id.ivdetailImg)
    }
}