package com.android.mvpapp.presenter

import com.android.mvpapp.model.ItemModel

interface MainContract {
    interface Views{
//        fun setupViews()
        fun setupListeners()
        fun getItem(): ItemModel
//        fun addItemToRecyclerViewList(list: ArrayList<ItemModel>)
//        fun addItemToRecyclerView(item: ItemModel)

    }
    interface MainViews{

        fun setupViews()
//        fun setupListeners()
        fun addItemToRecyclerViewList(list: ArrayList<ItemModel>)
        fun addItemToRecyclerView(item: ItemModel)

    }

    interface  Actions{
        fun initScreen()
        fun addItemToRecyclerView()
        fun addItemToDatabase()
        fun fetchAllItemsFromDatabase(  )
    }
}