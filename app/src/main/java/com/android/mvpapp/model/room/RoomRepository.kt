package com.android.mvpapp.model.room

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.android.mvpapp.app.InventoryApplication
import com.android.mvpapp.model.Item

class RoomRepository {
    private val itemDao: ItemDao = InventoryApplication.database.itemDao()

    private val allItems: LiveData<List<Item>>

    init {
        allItems = itemDao.getAllItems()
    }

    private class InsertAsyncTask internal constructor(private val dao: ItemDao) : AsyncTask<Item, Void, Void>() {
        override fun doInBackground(vararg params: Item): Void? {
            dao.insert(params[0])
            return null
        }
    }

    private class DeleteAsyncTask internal constructor(private val dao: ItemDao) : AsyncTask<Item, Void, Void>() {
        override fun doInBackground(vararg params: Item): Void? {
            dao.clearItem(*params)
            return null
        }
    }

    fun saveItem(item: Item) {
        InsertAsyncTask(itemDao).execute(item)
    }

    fun getAllItems() = allItems

    fun clearAllItems() {
        val itemArray = allItems.value?.toTypedArray()
        itemArray?.let {
            DeleteAsyncTask(itemDao).execute(*it)
        }
    }
}