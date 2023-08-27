package com.example.project_innogeeks

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project_innogeeks.Model.StoreDataType
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class viewModel: ViewModel() {
    var userList= MutableLiveData<List<StoreDataType>?>()
    var userList1 = mutableListOf<StoreDataType>()

   var mainActivity2=MainActivity2()

    //val category=mainActivity2.intent.getStringExtra("name")




    fun getdatafromFirebase(category: String) {
        var  databaseReference= FirebaseDatabase.getInstance().getReference().child("Proffesion").child(category)

        val firebasedata = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                val data = snapshot.value as? HashMap<String, HashMap<String, String>>
                Log.d("FirebaseData", "Data snapshot: $data")
                userList1.clear()
                if (data != null) {
                    for ((_, value) in data) {
                        val name = value["name"] ?: ""
                        val phone = value["phone"] ?: ""
                        val address = value["address"] ?: ""
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

    }
}