package com.example.project_innogeeks.VIewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project_innogeeks.Model.StoreDataType
import com.example.project_innogeeks.Model.jobs
import com.example.project_innogeeks.Repository.MyRepository

class MylistViewModel(private val myRepository: MyRepository) :ViewModel(){

  // val hello: MutableLiveData<List<StoreDataType>?> = myRepository.getdatafromFirebase()
  fun getdatafromFirebase(category: String): MutableLiveData<List<StoreDataType>?>{
     return myRepository.getdatafromFirebase(category)
  }


}