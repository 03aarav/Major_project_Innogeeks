package com.example.project_innogeeks

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.project_innogeeks.Model.PostType
import com.example.project_innogeeks.Model.UserType
import com.example.project_innogeeks.databinding.ActivityMakePostBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class MakePost : AppCompatActivity() {

    lateinit var binding: ActivityMakePostBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private lateinit var storage: StorageReference
    var uri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMakePostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth= FirebaseAuth.getInstance()

        val receivedAddress = intent.getStringExtra("currentAddress")

        binding.postImage.setOnClickListener {
            activityLauncher.launch("image/*")
            binding.uploadbtn.setOnClickListener {
                val name = binding.postText.text.toString()
                val phoneNo = binding.amount.text.toString()
                val Adress = receivedAddress.toString()
                //val category=binding.category.text.toString()



                if (name.isNotEmpty() && phoneNo.isNotEmpty() && Adress.isNotEmpty() && uri != null) {
                    uploadImageAndSaveUser(uri.toString(),name,phoneNo,Adress)
                } else {
                    Toast.makeText(this, "Please Enter the data and select an image", Toast.LENGTH_SHORT).show()
                }
            }
        }



    }













    private var activityLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) {
        uri = it
        binding.postImage.setImageURI(uri)
    }

    private fun uploadImageAndSaveUser(postimageurl:String,
                                        description:String,
                                        Amount:String,
                                       address: String,) {
        storage = FirebaseStorage.getInstance().reference.child("MyPhoto").child(auth.uid.toString())

        storage.putFile(uri!!).addOnSuccessListener { uploadTask ->
            uploadTask.storage.downloadUrl.addOnSuccessListener { imageUrl ->
                val user = PostType(imageUrl.toString(),description,Amount,FirebaseAuth.getInstance().currentUser?.uid.toString())
                databaseReference = FirebaseDatabase.getInstance().getReference()
                    .child("Post").child(FirebaseAuth.getInstance().currentUser?.uid.toString())

                databaseReference.setValue(user).addOnSuccessListener {
                    Toast.makeText(this, "User Added successfully", Toast.LENGTH_SHORT).show()
                }.addOnFailureListener {
                    Toast.makeText(this, "Unsuccessful", Toast.LENGTH_SHORT).show()
                }
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Image upload failed", Toast.LENGTH_SHORT).show()
        }
    }
}