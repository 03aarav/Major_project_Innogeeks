package com.example.project_innogeeks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.project_innogeeks.databinding.ActivityLogInBinding
import com.example.project_innogeeks.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUp : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivitySignUpBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth= FirebaseAuth.getInstance()
        binding.btnSignUp.setOnClickListener {
            signUp()
        }
        binding.btnLogin.setOnClickListener {
            val intent=Intent(this,LogIn::class.java)
            startActivity(intent)
        }


    }

    fun signUp(){
        val email=binding.etEmailAddress.text.toString()
        val pass1=binding.etPassword.text.toString()
        val pass2 =binding.etConfirmPassword.text.toString()


        if (email.isNotEmpty()&&pass1.isNotEmpty()&&pass1==pass2){
            auth.createUserWithEmailAndPassword(email,pass1).addOnSuccessListener {
                Toast.makeText(this, "Please Login Here", Toast.LENGTH_SHORT).show()
                val intent= Intent(this,LogIn::class.java)
                startActivity(intent)
            }
                .addOnFailureListener {
                    Toast.makeText(this, "Error occurred", Toast.LENGTH_SHORT).show()
                }
        }
        else{
            Toast.makeText(this, "Please Enter the email and password ", Toast.LENGTH_SHORT).show()
        }
    }
}
