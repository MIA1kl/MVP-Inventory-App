package com.android.mvpapp.model
import androidx.lifecycle.LiveData

interface ItemRepository {
    fun saveItem(item: Item)
    fun getAllItems(): LiveData<List<Item>>
    fun clearAllItems()
}