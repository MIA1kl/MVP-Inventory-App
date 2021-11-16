package com.android.mvpapp.model.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.mvpapp.model.Item

@Database(entities = [(Item::class)], version = 1)
abstract class ItemDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao
}