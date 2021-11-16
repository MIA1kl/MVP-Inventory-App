package com.android.mvpapp.model.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.android.mvpapp.model.Item

@Dao
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: Item)

    @Delete
    fun clearItem(vararg item: Item)

    @Query("SELECT * FROM inventory_table")
    fun getAllItems(): LiveData<List<Item>>
}