package com.example.drinkcountapp.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.drinkcountapp.data.Drink
import com.example.drinkcountapp.data.DrinkType
import com.example.drinkcountapp.data.StartRepository
import com.squareup.inject.assisted.AssistedInject
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.util.*

class StartViewModel constructor(
    private val repository: StartRepository) :
    ViewModel() {
    // TODO: Implement the ViewModel

    private var _isNewEntry = MutableLiveData<Boolean>()
    val isNewEntry: LiveData<Boolean>
        get() = _isNewEntry

    private var _coffeeCount = MutableLiveData<Int>()
    val coffeeCount: LiveData<Int>
        get() = _coffeeCount

    private var _waterCount = MutableLiveData<Int>()
    val waterCount: LiveData<Int>
        get() = _waterCount

    //private var _appDatabase: D

    init {
        resetFields()
    }

    fun onNewEntry() {
        _isNewEntry.value = true
    }

    fun onAddCoffee() {
        _coffeeCount.value = _coffeeCount.value?.plus(1)
    }

    fun onRmCoffee() {
        if (_coffeeCount.value!! > 0) _coffeeCount.value = _coffeeCount.value!!.minus(1)
    }

    fun onAddWater() {
        _waterCount.value = _waterCount.value!!.plus(1)
    }

    fun onRmWater() {
        if (_waterCount.value!! > 0) _waterCount.value = _waterCount.value!!.minus(1)
    }

    fun onSubmit() {
        viewModelScope.launch {
            convertToDrink().forEach {
                repository.addNewDrink(it)
            }
        }

        resetFields()
    }

    fun onCancel() {
        resetFields()
    }

    private fun resetFields() {
        _coffeeCount.value = 0
        _waterCount.value = 0
        _isNewEntry.value = false
    }

    private fun convertToDrink():List<Drink>{
        var drinks = LinkedList<Drink>()

        if (_coffeeCount.value != 0){
            drinks.add(Drink(
                localDateTime = LocalDateTime.now().toString(),
                drinkType = fromDrink(DrinkType.COFFEE),
                amount = _coffeeCount.value!!
            ))
        }

        if (_waterCount.value != 0){
            drinks.add(Drink(
                localDateTime = LocalDateTime.now().toString(),
                drinkType = fromDrink(DrinkType.WATER),
                amount = _coffeeCount.value!!
            ))
        }
        return drinks
    }

    private fun toDrink(value: String) = enumValueOf<DrinkType>(value)
    private fun fromDrink(value: DrinkType) = value.name
}