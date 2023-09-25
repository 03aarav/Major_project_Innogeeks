package com.example.project_innogeeks.VIewModelProvider

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.project_innogeeks.Repository.MyRepository
import com.example.project_innogeeks.VIewModel.MylistViewModel
import com.example.project_innogeeks.VIewModel.MyshowBidViewModel

class MyViewModelFactory2(private val repository: MyRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MylistViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MylistViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
