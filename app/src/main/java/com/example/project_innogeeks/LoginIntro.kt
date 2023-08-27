package com.example.project_innogeeks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class LoginInro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_inro)
        val btn=findViewById<Button>(R.id.btnGetStarted)

        btn.setOnClickListener {
            val intent=Intent(this,LogIn::class.java)
            startActivity(intent)
        }
    }
}