package com.example.project_innogeeks.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.project_innogeeks.Model.PostType
import com.example.project_innogeeks.Model.jobs
import com.example.project_innogeeks.R

class JobsPostAdapter(private val context: Context, private val postList: List<jobs>) :
    RecyclerView.Adapter<JobsPostAdapter.ViewHolder>() {

    // Define your ViewHolder
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val currentUidTextView: ImageView = itemView.findViewById(R.id.workimage)
        val amountTextView: TextView = itemView.findViewById(R.id.prices)
        val descriptionTextView: TextView = itemView.findViewById(R.id.Description)
        val btn:Button=itemView.findViewById(R.id.makebidbtn)
    }

    // Inflate the layout and create the ViewHolder instance
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.each_poat, parent, false)
        return ViewHolder(view)
    }

    // Bind data to the ViewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = postList[position]

        holder.btn.setOnClickListener {
            Toast.makeText(context, "Bid Successfully Made", Toast.LENGTH_SHORT).show()
        }
        holder.amountTextView.text = "Amount: ${post.amount}"
        holder.descriptionTextView.text = "Description: ${post.description}"

        Glide.with(holder.currentUidTextView)
            .load(post.postImageUrl)
            .into(holder.currentUidTextView)
    }

    // Return the number of items in the list
    override fun getItemCount(): Int {
        return postList.size
    }
}
