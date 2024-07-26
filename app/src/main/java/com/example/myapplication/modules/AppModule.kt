package com.example.myapplication.modules

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.myapplication.database.ShoppingDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {


    @Singleton
    @Provides
    fun shoppingDatabase(@ApplicationContext context: Context) : ShoppingDatabase {
        return Room.databaseBuilder(context, ShoppingDatabase::class.java,"Shopping_Database")
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                }
            })
            .setJournalMode(RoomDatabase.JournalMode.AUTOMATIC)
            .build()
    }
    @Singleton
    @Provides
    fun shoppingDao(shoppingDatabase: ShoppingDatabase) = shoppingDatabase.shoppingDao()

}