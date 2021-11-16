package com.android.mvpapp.app

import android.app.Application
import androidx.room.Room
import com.android.mvpapp.model.room.ItemDatabase

class InventoryApplication : Application() {

    companion object {
        lateinit var database: ItemDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this, ItemDatabase::class.java, "item_database").build()
    }
}