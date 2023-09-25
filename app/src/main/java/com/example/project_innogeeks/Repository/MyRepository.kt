package com.example.project_innogeeks.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.project_innogeeks.Model.StoreDataType
import com.example.project_innogeeks.Model.jobs
import com.example.project_innogeeks.MyClass.ShowBidding

class MyRepository(private val showBidding: ShowBidding) {

    fun showBidding(): LiveData<List<jobs>> {
       return showBidding.showbid()
   }
    fun getdatafromFirebase(category: String): MutableLiveData<List<StoreDataType>?> {
       return  showBidding.getdatafromFirebase( category)
    }

}