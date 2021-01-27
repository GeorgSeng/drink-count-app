package com.example.drinkcountapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.drinkcountapp.data.StartRepository
import java.lang.IllegalArgumentException

class StartViewModelFactory(
    private val repository: StartRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(StartViewModel::class.java)){
            return StartViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}