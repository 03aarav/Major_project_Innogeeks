package com.example.project_innogeeks

import android.Manifest
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.project_innogeeks.Model.UserType
import com.example.project_innogeeks.databinding.ActivityRegisterBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.IOException
import java.util.*

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private lateinit var storage: StorageReference
    var uri: Uri? = null
    var selCategory: String? = ""

        private lateinit var fusedLocationClient: FusedLocationProviderClient
        private lateinit var addTextView: TextView
        private lateinit var geocoder: Geocoder


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth= FirebaseAuth.getInstance()
        var spinneroption_category = findViewById<Spinner>(R.id.category)



        val options = resources.getStringArray(R.array.predefined_profeesion_category)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, options)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinneroption_category.adapter = adapter
        binding.Adress.setOnClickListener {
            geocoder = Geocoder(this, Locale.getDefault())
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
            fetchLocationAndDisplayAddress(binding.Adress)

        }

        binding.timg.setOnClickListener {
            activityLauncher.launch("image/*")
            binding.tbtn.setOnClickListener {
                val name = binding.tname.text.toString()
                val phoneNo = binding.tphone.text.toString()
                val Adress = binding.Adress.text.toString()
                //val category=binding.category.text.toString()
                val about=binding.AboutYourself.text.toString()
                selCategory = spinneroption_category.selectedItem.toString()


                if (name.isNotEmpty() && phoneNo.isNotEmpty() && Adress.isNotEmpty() && uri != null&&selCategory.toString().isNotEmpty()&&about.isNotEmpty()) {
                    uploadImageAndSaveUser(name, phoneNo, Adress, selCategory.toString(),about)
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
        binding.timg.setImageURI(uri)
    }


    private fun uploadImageAndSaveUser(name: String, phoneNo: String, Adrress:String,Category:String,about:String) {
        storage = FirebaseStorage.getInstance().reference.child("MyPhoto").child(auth.uid.toString())

        storage.putFile(uri!!).addOnSuccessListener { uploadTask ->
            uploadTask.storage.downloadUrl.addOnSuccessListener { imageUrl ->
                val user = UserType(name,phoneNo,Adrress,Category,about,imageUrl.toString(),FirebaseAuth.getInstance().currentUser?.uid.toString())
                databaseReference = FirebaseDatabase.getInstance().getReference()
                    .child("Proffesion").child(Category).child(auth.uid.toString())

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

        private fun fetchLocationAndDisplayAddress(addressTextView: TextView) {
            // Check for location permissions
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                // Permission granted, fetch location
                fusedLocationClient.lastLocation
                    .addOnSuccessListener { location: Location? ->
                        if (location != null) {
                            displayAddress(location,addressTextView)
                        } else {
                            addressTextView.text = "Location not available"
                        }
                    }
                    .addOnFailureListener { exception ->
                        addressTextView.text = "Failed to get location"
                        exception.printStackTrace()
                    }
            } else {
                // Request location permission
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    LOCATION_PERMISSION_REQUEST_CODE
                )
            }
        }

        private fun displayAddress(location: Location, addressTextView: TextView) {
            try {
                val addresses: List<Address> = geocoder.getFromLocation(
                    location.latitude,
                    location.longitude,
                    1
                )!!
                if (addresses.isNotEmpty()) {
                    val address: Address = addresses[0]
                    val addressText = address.getAddressLine(0)
                    addressTextView.text = addressText
                } else {
                    addressTextView.text = "Address not found"
                }
            } catch (e: IOException) {
                addressTextView.text = "Error retrieving address"
                e.printStackTrace()
            }
        }

        companion object {
            private const val LOCATION_PERMISSION_REQUEST_CODE = 123
        }
}