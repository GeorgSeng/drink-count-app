package com.example.drinkcountapp.data

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StartRepository @Inject constructor(private val drinkDao: DrinkDao){

    suspend fun addNewDrink(drink: Drink){
        drinkDao.insert(drink)
    }
}