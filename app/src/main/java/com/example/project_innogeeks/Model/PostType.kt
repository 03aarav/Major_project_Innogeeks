package com.example.project_innogeeks.Model

import android.location.Address

data class PostType(
    val postimageurl:String,
    val description:String,
    val Amount:String,
    val currentUid:String,
    //val address: String,
//    val currentUid: String,
//    val postImageUrl: String,
//    val amount: String,
//    val description: String
)
{
    constructor():this("","","","")
}
