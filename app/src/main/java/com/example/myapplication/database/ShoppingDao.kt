package com.example.myapplication.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.myapplication.dataClasses.ShoppingItem

@Dao
interface ShoppingDao {

    @Upsert
    fun addShoppingItem(shoppingItem: ShoppingItem)

    @Delete
    fun deleteShoppingItem(shoppingItem: ShoppingItem)

    @Query("SELECT * FROM ShoppingItem")
    fun getAllShoppingItems() : List<ShoppingItem>

}