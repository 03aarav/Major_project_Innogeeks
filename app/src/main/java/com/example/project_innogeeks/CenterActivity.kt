package com.example.project_innogeeks

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project_innogeeks.Adapter.FeatureAdapter
import com.example.project_innogeeks.Model.FeatureType
import com.example.project_innogeeks.databinding.ActivityCenterBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.firebase.auth.FirebaseAuth
import org.w3c.dom.Text
import java.io.IOException
import java.security.acl.Group
import java.util.*

class CenterActivity : AppCompatActivity() {
    lateinit var binding: ActivityCenterBinding
    lateinit var featureAdapter: FeatureAdapter
    private lateinit var auth: FirebaseAuth
    private lateinit var sharedPreferences: SharedPreferences



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCenterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()
        sharedPreferences = getSharedPreferences("myPreferences", Context.MODE_PRIVATE)



        val feature1 = FeatureType("Search Profession", R.drawable.images)
        val feture2 = FeatureType("Make Bidding", R.drawable.biding)
        val feature3 = FeatureType("Search Query", R.drawable.a)
        val feature4 = FeatureType("Register", R.drawable.sss)


        val listtype = mutableListOf(feature1, feture2, feature3, feature4)
        binding.featureRecylerview.layoutManager = GridLayoutManager(this, 2)

        featureAdapter = FeatureAdapter(this, listtype)

        binding.featureRecylerview.adapter = featureAdapter


        binding.logOutbtn.setOnClickListener {
            auth.signOut()
            sharedPreferences.edit().clear().apply()
            val intent = Intent(this, LogIn::class.java)
            startActivity(intent)
            finish()
        }


       // val userAuthToken = FirebaseAuth.getInstance().currentUser?.getIdToken(true)


    }
}

