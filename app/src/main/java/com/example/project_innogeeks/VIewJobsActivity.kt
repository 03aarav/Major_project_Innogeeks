package com.example.project_innogeeks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project_innogeeks.Adapter.JobsPostAdapter
import com.example.project_innogeeks.Model.PostType
import com.example.project_innogeeks.Model.jobs
import com.example.project_innogeeks.MyClass.ShowBidding
import com.example.project_innogeeks.Repository.MyRepository
import com.example.project_innogeeks.VIewModel.MyshowBidViewModel
import com.example.project_innogeeks.databinding.ActivityViewJobsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class VIewJobsActivity : AppCompatActivity() {
    lateinit var databaseReference: DatabaseReference
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var binding: ActivityViewJobsBinding
   // lateinit var postjobslist:MutableList<jobs>
    lateinit var jobsPostAdapter: JobsPostAdapter
    lateinit var myshowBidViewModel: MyshowBidViewModel
    lateinit var myViewModelFactory: MyViewModelFactory
    lateinit var myRepository: MyRepository
    lateinit var showBidding: ShowBidding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityViewJobsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

//        databaseReference = FirebaseDatabase.getInstance().getReference()
//            .child("Post")
        binding.recyclerView.layoutManager=LinearLayoutManager(this)
       showBidding= ShowBidding()

        myRepository= MyRepository(showBidding)
        myViewModelFactory= MyViewModelFactory(myRepository)
        myshowBidViewModel=ViewModelProvider(this,myViewModelFactory).get(MyshowBidViewModel::class.java)

        myshowBidViewModel.listofbid.observe(this){
            jobsPostAdapter=JobsPostAdapter(this@VIewJobsActivity,it)
                    binding.recyclerView.adapter=jobsPostAdapter
            jobsPostAdapter.notifyDataSetChanged()
        }


//        val fireabasedata= object :ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                postjobslist= mutableListOf()
//                Log.d("xyz",snapshot.toString())
//                postjobslist.clear()
//
//                for (childSnapshot in snapshot.children) {
//                    val currentUid = childSnapshot.child("currentUid").getValue(String::class.java)
//                    val postImageUrl = childSnapshot.child("postimageurl").getValue(String::class.java)
//                    val amount = childSnapshot.child("amount").getValue(String::class.java)
//                    val description = childSnapshot.child("description").getValue(String::class.java)
//
//                    if (currentUid != null && postImageUrl != null && amount != null && description != null) {
//                        val post = jobs(currentUid, postImageUrl, amount, description)
//                        postjobslist.add(post)
//
//                    }
//                    jobsPostAdapter=JobsPostAdapter(this@VIewJobsActivity,postjobslist)
//                    binding.recyclerView.adapter=jobsPostAdapter
//                }
//
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//
//        }
//
//        databaseReference.addValueEventListener(fireabasedata)
    }
}