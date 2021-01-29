package com.example.drinkcountapp.data

class DrinkHistoryRepository constructor(private val drinkDao: DrinkDao) {
    suspend fun getAll(): List<Drink> {
        return drinkDao.getAll()
    }
}