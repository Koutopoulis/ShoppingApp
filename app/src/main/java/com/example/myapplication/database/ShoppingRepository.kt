package com.example.myapplication.database

import com.example.myapplication.dataClasses.ShoppingItem
import javax.inject.Inject

class ShoppingRepository @Inject constructor(private val shoppingDao: ShoppingDao) {

    fun addShoppingItem(shoppingItem: ShoppingItem) = shoppingDao.addShoppingItem(shoppingItem)

    fun deleteShoppingItem(shoppingItem: ShoppingItem) = shoppingDao.deleteShoppingItem(shoppingItem)

    fun getAllShoppingItems() = shoppingDao.getAllShoppingItems()

}