package com.example.project_innogeeks.Model

import java.io.WriteAbortedException

data class StoreDataType(
    val about:String,
    val address:String,
    val category:String,
    val Imageurl:String,
    val name:String,
    val phone:String,
    val uid:String
)
{
    constructor():this("","","","","","","")
}
