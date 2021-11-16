package com.android.mvpapp.presenter

import androidx.lifecycle.LiveData
import com.android.mvpapp.model.Item

interface AllItemsContract {

    interface Presenter{
        fun getAllItems(): LiveData<List<Item>>
        fun clearAllItems()
    }

    interface View{
        fun showItemCleared()
    }

}