package com.example.project_innogeeks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project_innogeeks.Adapter.MyChatAdapter
import com.example.project_innogeeks.Model.ChatType
import com.example.project_innogeeks.Model.UserType
import com.example.project_innogeeks.databinding.ActivityChatBinding
import com.example.project_innogeeks.databinding.ActivityLogInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.auth.User

class ChatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatBinding
    var firebaseUser: FirebaseUser? = null
    var databaseReference: DatabaseReference? = null
     lateinit var chatList : MutableList<ChatType>
     lateinit var myChatAdapter: MyChatAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var username= intent.getStringExtra("Username")
        var recieveruid=intent.getStringExtra("RecieverUid")
        val senderuid=FirebaseAuth.getInstance().currentUser?.uid.toString()

        chatList= mutableListOf()

        databaseReference=FirebaseDatabase.getInstance().getReference()

       binding.chatRecyclerView.layoutManager=LinearLayoutManager(this)

        myChatAdapter= MyChatAdapter(this,chatList)
        binding.chatRecyclerView.adapter=myChatAdapter

        var SenderRoom=recieveruid+senderuid
        var RecieverRoom=senderuid+recieveruid

        binding.btnSendMessage.setOnClickListener {
            val message1=binding.etMessage.text.toString()
            val messageObj= ChatType(message1,senderuid)

            databaseReference!!.child("Chat").child(SenderRoom).child("message").push()
                .setValue(messageObj).addOnSuccessListener {
                    databaseReference!!.child("Chat").child(RecieverRoom).child("message").push()
                        .setValue(messageObj)
                }
            binding.etMessage.text.clear()
        }


        databaseReference!!.child("Chat").child(SenderRoom).child("message")
            .addValueEventListener(object: ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    chatList.clear()
                    for (snap in snapshot.children) {
                        val message = snap.getValue(ChatType::class.java)
                        chatList.add(message!!)
                    }
                      myChatAdapter.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            } )










//        binding.chatRecyclerView.layoutManager= LinearLayoutManager(this, RecyclerView.VERTICAL, false)
//        var intent= getIntent()
//        var userId= intent.getStringExtra("Username")
//        firebaseUser=FirebaseAuth.getInstance().currentUser
//        reference=FirebaseDatabase.getInstance().getReference("UsersType").child(userId!!)
//        reference!!.addValueEventListener(object :ValueEventListener{
//            override fun onDataChange(snapshot: DataSnapshot) {
//                val user = snapshot.getValue(UserType::class.java)
//
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//
//        })
//        binding.btnSendMessage.setOnClickListener{
//            var message: String= binding.etMessage.text.toString()
//            if (message.isEmpty()){
//                Toast.makeText(applicationContext, "message is empty", Toast.LENGTH_SHORT).show()
//                binding.etMessage.setText("")
//            }else{
//                sendMessage(firebaseUser!!.uid, userId, message)
//                binding.etMessage.setText("")
//            }
//        }
//        readMessage(firebaseUser!!.uid, userId)
//    }
//    private fun sendMessage(senderId: String,receiverId: String, message: String){
//        var reference: DatabaseReference? = FirebaseDatabase.getInstance().getReference()
//        var hashMap: HashMap<String, String> = HashMap()
//        hashMap.put("senderId", senderId)
//        hashMap.put("receiverId", receiverId)
//        hashMap.put("message", message)
//        reference!!.child("ChatType").push().setValue(hashMap)
//    }
//    fun readMessage(senderId: String, receiverId: String){
//        val databaseReference: DatabaseReference =
//            FirebaseDatabase.getInstance().getReference("ChatType")
//        databaseReference.addValueEventListener(object : ValueEventListener{
//            override fun onDataChange(snapshot: DataSnapshot) {
//                chatList.clear()
//                for (dataSnapShot: DataSnapshot in snapshot.children){
//                    val chat = dataSnapShot.getValue(ChatType::class.java)
//                    if(chat!!.senderId.equals(senderId)&&chat!!.receiverId.equals(receiverId) ||
//                        chat!!.senderId.equals(receiverId)&&chat!!.receiverId.equals(senderId) ){
//                        chatList.add(chat)
//                    }
//                }
//                val chatAdapter =MyChatAdapter(this@ChatActivity, chatList)
//                binding.chatRecyclerView.adapter= chatAdapter
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//
//        }
//        )
//    }
    }
}