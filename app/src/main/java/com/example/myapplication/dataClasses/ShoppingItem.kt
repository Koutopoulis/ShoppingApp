package com.example.myapplication.dataClasses

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity
data class ShoppingItem(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    var description: String,
    var price: BigDecimal,
    var size: String,
    var stock: BigDecimal,
)

