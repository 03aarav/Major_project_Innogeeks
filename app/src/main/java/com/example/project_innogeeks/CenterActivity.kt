package com.example.project_innogeeks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project_innogeeks.Adapter.FeatureAdapter
import com.example.project_innogeeks.Model.FeatureType
import com.example.project_innogeeks.databinding.ActivityCenterBinding
import java.security.acl.Group

class CenterActivity : AppCompatActivity() {
    lateinit var binding: ActivityCenterBinding
    lateinit var featureAdapter: FeatureAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityCenterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val feature1=FeatureType("Search People",R.drawable.images)
        val feture2=FeatureType("Make Bidding",R.drawable.biding)
        val feature3=FeatureType("Search Query",R.drawable.a)
        val feature4=FeatureType("Register",R.drawable.sss)

        val listtype= mutableListOf(feature1,feture2,feature3,feature4)
        binding.featureRecylerview.layoutManager=LinearLayoutManager(this)

        featureAdapter=FeatureAdapter(this,listtype)

        binding.featureRecylerview.adapter=featureAdapter






    }
}