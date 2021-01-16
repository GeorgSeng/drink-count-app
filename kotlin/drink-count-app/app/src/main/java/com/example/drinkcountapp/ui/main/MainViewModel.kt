package com.example.drinkcountapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    private var coffeeCount: LiveData<Int> = MutableLiveData<Int>();
    val CoffeeCount: LiveData<Int>
        get() = coffeeCount

    private var waterCount: LiveData<Int> = MutableLiveData<Int>();
    val WaterCount: LiveData<Int>
        get() = waterCount

    init {
        //coffeeCount.value = 0
    }

    fun onAddCoffee(){
        coffeeCount.value!!.plus(1)
    }
    fun onRmCoffee(){
        coffeeCount.value!!.minus(1)
    }

    fun onAddWater(){
        waterCount.value!!.plus(1)
    }
    fun onRmWater(){
        waterCount.value!!.minus(1)
    }
}