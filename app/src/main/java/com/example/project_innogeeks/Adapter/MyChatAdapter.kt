package com.example.project_innogeeks.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.project_innogeeks.ChatActivity
import com.example.project_innogeeks.Model.ChatType
import com.example.project_innogeeks.Model.StoreDataType
import com.example.project_innogeeks.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MyChatAdapter(private val context: Context,private val chatList: List<ChatType>) :
    RecyclerView.Adapter<MyChatAdapter.ItemViewHolder>() {
    private val MESSAGE_TYPE_LEFT = 0
    private val MESSAGE_TYPE_RIGHT = 1
    var firebaseUser:FirebaseUser?= null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        if (viewType==MESSAGE_TYPE_RIGHT) {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_right, parent, false)
            return ItemViewHolder(view)

        }
        else{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_left, parent, false)
            return ItemViewHolder(view)
        }

    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val chat = chatList[position]
        holder.tname.text= chat.message
    }

    override fun getItemCount(): Int {
        return chatList.size
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tname=itemView.findViewById<TextView>(R.id.tvMessage)
        val image=itemView.findViewById<ImageView>(R.id.timage)
    }

    override fun getItemViewType(position: Int): Int {
        firebaseUser= FirebaseAuth.getInstance().currentUser
        if(
            chatList[position].senderId==firebaseUser!!.uid
        )
            return MESSAGE_TYPE_RIGHT
        else
            return MESSAGE_TYPE_LEFT

    }
}