package com.example.project_innogeeks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.project_innogeeks.databinding.ActivityLogInBinding
import com.google.firebase.auth.FirebaseAuth

class LogIn : AppCompatActivity() {
    private lateinit var binding: ActivityLogInBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth= FirebaseAuth.getInstance()

        binding.btnSignin.setOnClickListener {
            val intent= Intent(this,SignUp::class.java)
            startActivity(intent)
        }
        binding.btnLogin.setOnClickListener {
            signIn()
        }

    }



    fun signIn(){
        val email=binding.etEmailAddress.text.toString()
        val pass1=binding.etPassword.text.toString()
        if (email.isNotEmpty()&&pass1.isNotEmpty()){
            auth.signInWithEmailAndPassword(email,pass1).addOnSuccessListener {
                val intent= Intent(this,MainActivity::class.java)
                startActivity(intent)
                binding.etEmailAddress.text.clear()
                binding.etPassword.text.clear()
            }.addOnFailureListener {
                Toast.makeText(this, "User does not exist", Toast.LENGTH_SHORT).show()
                binding.etEmailAddress.text.clear()
                binding.etPassword.text.clear()
            }

        }
        else{
            Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
        }


    }
}