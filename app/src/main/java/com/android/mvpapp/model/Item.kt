package com.android.mvpapp.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "inventory_table")
data class Item(
    @PrimaryKey @NonNull val name: String = "",
    @NonNull val price: Int = 0,
    @NonNull val quantity: Int = 0,
    @NonNull val supplier: String = "",
    val drawable: Int = 0
)