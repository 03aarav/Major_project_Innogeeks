package com.example.project_innogeeks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project_innogeeks.Adapter.MyListAdapter
import com.example.project_innogeeks.Model.StoreDataType
import com.example.project_innogeeks.databinding.ActivityMain2Binding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity2 : AppCompatActivity() {
    lateinit var binding: ActivityMain2Binding
    private lateinit var myListAdapter: MyListAdapter
    private lateinit var databaseReference: DatabaseReference
    lateinit var viewModeel: viewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val category=intent.getStringExtra("name")
        binding.category1.text=category

        databaseReference= FirebaseDatabase.getInstance().getReference()
        binding.recyler.layoutManager= LinearLayoutManager(this)
        viewModeel = ViewModelProvider(this)[viewModel::class.java]
//        binding.user.setOnClickListener {
//            val intent= Intent(this,RegisterActivity::class.java)
//            startActivity(intent)
//        }

        viewModeel.getdatafromFirebase(category.toString())
        viewModeel.userList.observe(this){

            myListAdapter= MyListAdapter(this, it as MutableList<StoreDataType>)
            binding.recyler.adapter=myListAdapter

        }


       }
    }
