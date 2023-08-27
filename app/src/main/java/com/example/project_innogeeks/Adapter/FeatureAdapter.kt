package com.example.project_innogeeks.Adapter


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.project_innogeeks.Model.FeatureType
import com.example.project_innogeeks.R
import com.example.project_innogeeks.RegisterActivity

class FeatureAdapter(private val context: Context,private val items: List<FeatureType>) : RecyclerView.Adapter<FeatureAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.each_block_2, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.feature.text=item.name
        Glide.with(holder.featureimage)
            .load(item.featureImage)
            .into(holder.featureimage)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
       val feature=itemView.findViewById<TextView>(R.id.TittleCenter)
        val featureimage=itemView.findViewById<ImageView>(R.id.ImageCenter)


    }
}
