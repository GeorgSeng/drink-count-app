package com.example.drinkcountapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DrinkDao {
    @Insert
    suspend fun insert(entity: Drink)

    @Query("select * from drink")
    fun getAll(): List<Drink>
}
