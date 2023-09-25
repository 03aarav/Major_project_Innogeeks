package com.example.project_innogeeks.MyClass

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.project_innogeeks.Adapter.JobsPostAdapter
import com.example.project_innogeeks.MainActivity2
import com.example.project_innogeeks.Model.StoreDataType
import com.example.project_innogeeks.Model.jobs
import com.google.firebase.database.*

class ShowBidding {
    private val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReference().child("Post")

    var mainActivity2= MainActivity2()
    // Change the return type to LiveData


    fun showbid(): LiveData<List<jobs>> {
        val postjobslist = mutableListOf<jobs>()
        val postjobslist1 = MutableLiveData<List<jobs>>() // Initialize LiveData

        val fireabasedata = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                postjobslist.clear()

                for (childSnapshot in snapshot.children) {
                    val currentUid = childSnapshot.child("currentUid").getValue(String::class.java)
                    val postImageUrl = childSnapshot.child("postimageurl").getValue(String::class.java)
                    val amount = childSnapshot.child("amount").getValue(String::class.java)
                    val description = childSnapshot.child("description").getValue(String::class.java)

                    if (currentUid != null && postImageUrl != null && amount != null && description != null) {
                        val post = jobs(currentUid, postImageUrl, amount, description)
                        postjobslist.add(post)
                    }
                }

                // Set the LiveData value here
                postjobslist1.postValue(postjobslist)
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        }

        databaseReference.addValueEventListener(fireabasedata)

        // Return LiveData
        return postjobslist1
    }

    fun getdatafromFirebase(category: String) : MutableLiveData<List<StoreDataType>?> {


        var  databaseReference= FirebaseDatabase.getInstance().getReference().child("Proffesion").child(category)
        var userList= MutableLiveData<List<StoreDataType>?>()
        var userList1 = mutableListOf<StoreDataType>()
        val firebasedata = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                val data = snapshot.value as? HashMap<String, HashMap<String, String>>
                Log.d("FirebaseData", "Data snapshot: $data")
                userList1.clear()
                if (data != null) {
                    for ((_, value) in data) {
                        val name = value["name"] ?: ""
                        val phone = value["phone"] ?: ""
                        val address = value["addrress"] ?: ""
                        val downurl = value["imagUrl"] ?: ""
                        val uid =value["uid"]?:""
                        val about=value["abortPolicy"]?:""
                        val category=value["category"]?:""
                        val user1 = StoreDataType(about,address,category,downurl,name,phone,uid)
                        userList1.add(user1)

                    }
                    userList.postValue(userList1)

                }

            }

            override fun onCancelled(error: DatabaseError) {

            }

        }
        databaseReference.addValueEventListener(firebasedata)
//return the value
        return userList
    }
}




