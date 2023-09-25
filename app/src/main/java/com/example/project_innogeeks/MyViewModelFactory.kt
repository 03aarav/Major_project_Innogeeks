package com.example.project_innogeeks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.project_innogeeks.Repository.MyRepository
import com.example.project_innogeeks.VIewModel.MyshowBidViewModel

class MyViewModelFactory(private val repository: MyRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyshowBidViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MyshowBidViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
