package com.example.carfx.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.carfx.repository.MainRepository
import java.lang.RuntimeException

class MainViewModelFactory(private val mainRepository: MainRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass == MainViewModel::class.java) {
            return MainViewModel(mainRepository) as T
        }
        throw RuntimeException("Error creating Viewmodel")
    }
}