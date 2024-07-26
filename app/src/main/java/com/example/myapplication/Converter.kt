package com.example.myapplication

import androidx.room.TypeConverter
import java.math.BigDecimal
import java.time.Instant

class Converters {
    @TypeConverter
    fun bigDecimalToString(value: String?): BigDecimal? {
        return value?.toBigDecimalOrNull()
    }

    @TypeConverter
    fun stringToBigDecimal(bigDecimal: BigDecimal) : String {
        return bigDecimal.toString()
    }
}