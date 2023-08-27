package com.example.project_innogeeks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project_innogeeks.Adapter.MyMainAdapter
import com.example.project_innogeeks.Model.ProfeesionType
import com.example.project_innogeeks.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var myMainAdapter: MyMainAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val prof1=ProfeesionType("Barber",R.drawable.barber)
        val prof2=ProfeesionType("Carpenter",R.drawable.carpenter)
        val prof3=ProfeesionType("Electrician",R.drawable.electrician)
        val prof4=ProfeesionType("fireman",R.drawable.fireman)
        val prof5=ProfeesionType("painter",R.drawable.painter)
        val prof6=ProfeesionType("Mechanic",R.drawable.mechanic)
        val prof7=ProfeesionType("Worker",R.drawable.workers)
        val prof8=ProfeesionType("Tailor",R.drawable.tailor)
        val prof9=ProfeesionType("Doctor",R.drawable.doctor)
        val prof10=ProfeesionType("Teacher",R.drawable.teacher)
        val prof11=ProfeesionType("Pharmacist",R.drawable.pharmacist)
        val prof12=ProfeesionType("Janitor",R.drawable.janitor)
        val prof13=ProfeesionType("BasicNeed",R.drawable.basicneeds)



        val listofProffesion= mutableListOf(prof1,prof2,prof3,prof4,prof5,prof6,prof7,prof8,prof9,prof10,prof11,prof12,prof13)

        binding.recylerview.layoutManager=GridLayoutManager(this,2)
        myMainAdapter= MyMainAdapter(this,listofProffesion)
        binding.recylerview.adapter=myMainAdapter

        binding.Register.setOnClickListener {
            val intent= Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }




    }
}