package com.example.myapplication.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myapplication.Converters
import com.example.myapplication.dataClasses.ShoppingItem

@Database(
    entities = [
        ShoppingItem::class,

    ],
    version = 1, exportSchema = false
)
@TypeConverters(Converters::class)
abstract class ShoppingDatabase : RoomDatabase() {
    abstract fun shoppingDao(): ShoppingDao
}