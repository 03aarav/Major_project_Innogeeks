package com.example.project_innogeeks.VIewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project_innogeeks.Model.jobs
import com.example.project_innogeeks.Repository.MyRepository

class MyshowBidViewModel(private val myRepository: MyRepository):ViewModel() {

     var  listofbid: LiveData<List<jobs>> =myRepository.showBidding()


}