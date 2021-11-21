package com.android.mvpapp.view.allitems

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