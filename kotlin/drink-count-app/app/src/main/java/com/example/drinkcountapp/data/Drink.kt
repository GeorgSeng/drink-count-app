package com.example.drinkcountapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.time.LocalDateTime


@Entity(tableName = "drink")
//@TypeConverters(Converters::class)
data class Drink(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
//    val time: LocalDateTime,
//    val drinkType: DrinkType,
    val localDateTime: String,
    var drinkType: String,
    val amount: Int
)
