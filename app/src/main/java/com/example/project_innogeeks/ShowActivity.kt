package com.example.project_innogeeks

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.project_innogeeks.Adapter.ReviewAdapter
import com.example.project_innogeeks.Model.Review
import com.example.project_innogeeks.databinding.ActivityShowBinding
import com.google.firebase.database.*

class ShowActivity : AppCompatActivity() {
    lateinit var binding:ActivityShowBinding
    lateinit var databaseReference: DatabaseReference
    //lateinit var ReviewList:MutableList<Review>
    lateinit var reviewAdapter: ReviewAdapter
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityShowBinding.inflate(layoutInflater)
        setContentView(binding.root)
       // ReviewList= mutableListOf()

        val name= intent.getStringExtra("name")
        val address= intent.getStringExtra("address")
        val phone= intent.getStringExtra("phone")
        val cahrge= intent.getStringExtra("charge")
        val uid= intent.getStringExtra("uid")
        val imageurl= intent.getStringExtra("image")

        binding.name.text=name.toString()
        binding.address.text="Address + ${address}"
        binding.number.text="Contact number: $phone"
        binding.Charge.text="Charge : $cahrge"



        Glide.with(binding.imageView)
            .load(imageurl)
            .into(binding.imageView)
        binding.postreview.setOnClickListener {
            var review=binding.Review.text.toString()
            databaseReference=FirebaseDatabase.getInstance().getReference().child("Review").child(uid.toString())
                .child(System.currentTimeMillis().toString())
            databaseReference.setValue(review).addOnSuccessListener {
                Toast.makeText(this, "Review added succesfully", Toast.LENGTH_SHORT).show()
                binding.Review.text.clear()
            }

        }
        binding.reviewRecyler.layoutManager=LinearLayoutManager(this)


   binding.showreviewbtn.setOnClickListener {
       databaseReference=FirebaseDatabase.getInstance().getReference().child("Review").child(uid.toString())
       val valueEventListener= object : ValueEventListener {
           override fun onDataChange(snapshot: DataSnapshot) {
               Log.d("Data",snapshot.toString())
               val    ReviewList = mutableListOf<Review>()
               for (childSnapshot in snapshot.children) {
                   val timestamp = childSnapshot.key?.toLongOrNull()
                   val reviewText = childSnapshot.value as? String

                   if (timestamp != null && reviewText != null) {
                       val review = Review(reviewText, timestamp)
                       ReviewList.add(review)
                   }
               }
               reviewAdapter=ReviewAdapter(ReviewList)
               binding.reviewRecyler.adapter=reviewAdapter
           }
           override fun onCancelled(error: DatabaseError) {

               Toast.makeText(this@ShowActivity, "Error fetching the review", Toast.LENGTH_SHORT).show()
           }
       }

       databaseReference.addValueEventListener(valueEventListener)

   }



    }
}

