package com.example.drinkcountapp.data

import androidx.room.TypeConverter
import java.time.LocalDateTime

class Converters {

    @TypeConverter
    fun toDrink(value: String) = enumValueOf<DrinkType>(value)

    @TypeConverter
    fun fromDrink(value: DrinkType) = value.name
//
//    @TypeConverter
//    fun toLocalDateTime(value: String): LocalDateTime = LocalDateTime.parse(value)
//
//    @TypeConverter
//    fun fromLocalDateTime(value: LocalDateTime) = value.toString()
}