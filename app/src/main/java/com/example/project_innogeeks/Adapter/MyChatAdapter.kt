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

class MyChatAdapter(private var context: ChatActivity, private var messageList: List<ChatType>)
    :RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private val item_sent=1
    private val item_reccieve=2

    class SentMessageViewHolder(view: View):RecyclerView.ViewHolder(view){
        val sentmessage=view.findViewById<TextView>(R.id.sent_message)

    }
    class RecieveMessageViewHolder(view: View):RecyclerView.ViewHolder(view){
        val  recieveMessage=view.findViewById<TextView>(R.id.received_message)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType==1){
            //inflate sent
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_right, parent, false)
            return SentMessageViewHolder(view)
        }
        else {
            var view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_left, parent, false)
            return RecieveMessageViewHolder(view)
        }
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val currentmessage=messageList[position]
        if (holder::class.java==SentMessageViewHolder::class.java){
            //do task related to sent message
            val viewHolder=holder as SentMessageViewHolder
            viewHolder.sentmessage.text=currentmessage.message
        }
        else
        {
            //Do task related to recieve message

            val viewHolder=holder as RecieveMessageViewHolder
            viewHolder.recieveMessage.text=currentmessage.message

        }
    }

    override fun getItemViewType(position: Int): Int {
        val currentmessage=messageList[position]
        if(FirebaseAuth.getInstance().currentUser?.uid.toString()==currentmessage.UID){
            return item_sent
        }
        else
            return item_reccieve

    }
}