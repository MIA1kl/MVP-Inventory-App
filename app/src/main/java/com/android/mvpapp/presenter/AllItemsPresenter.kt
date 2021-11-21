package com.android.mvpapp.presenter

import androidx.lifecycle.LiveData
import com.android.mvpapp.model.Item
import com.android.mvpapp.model.room.RoomRepository
import com.android.mvpapp.view.allitems.AllItemsContract

class AllItemsPresenter(private val repository: RoomRepository = RoomRepository()):
    BasePresenter<AllItemsContract.View>(), AllItemsContract.Presenter{

    override fun getAllItems(): LiveData<List<Item>> {
        return repository.getAllItems()
    }

    override fun clearAllItems() {
        repository.clearAllItems()
        getView()?.showItemCleared()
    }


}