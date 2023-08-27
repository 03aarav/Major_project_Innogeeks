package com.example.project_innogeeks.Model

import java.util.concurrent.ThreadPoolExecutor.AbortPolicy

data class UserType(
    val Name:String,
    val phone:String,
    val Addrress:String,
    val category: String,
    val abortPolicy: String,
    val imagUrl:String,
    val uid:String

)
{
    constructor():this("","","","","","","")
}
