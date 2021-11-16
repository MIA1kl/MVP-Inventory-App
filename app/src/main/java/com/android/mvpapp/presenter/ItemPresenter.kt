package com.android.mvpapp.presenter

import com.android.mvpapp.model.Item
import com.android.mvpapp.model.ItemGenerator
import com.android.mvpapp.model.room.RoomRepository

class ItemPresenter(private val itemGenerator: ItemGenerator = ItemGenerator(),
                    private val repository: RoomRepository = RoomRepository()) :
    BasePresenter<MainContract.View>(), MainContract.Presenter {

    private lateinit var item: Item

    private var name = ""
    private var price = 0
    private var quantity = 0
    private var supplier = ""
    private var drawable = 0

    override fun updateName(name: String) {
        this.name = name
        updateItem()
    }

    override fun updatePrice(price: Int) {
        this.price = price
        updateItem()
    }
    override fun updateQuantity(quantity:Int) {
        this.quantity = quantity
        updateItem()
    }
    override fun updateSupplier(supplier:String) {
        this.supplier =  supplier
        updateItem()
    }
    override fun drawableSelected(drawable: Int) {
        this.drawable = drawable
        getView()?.showAvatarDrawable(drawable)
        updateItem()
    }

    override fun isDrawableSelected(): Boolean {
        return drawable != 0
    }

    override fun saveItem() {
        if (canSaveItem()) {
            repository.saveItem(item)
            getView()?.showItemSaved()
        } else {
            getView()?.showItemSavedError()
        }
    }

    private fun updateItem() {
        item = itemGenerator.generateItem( name,price,quantity,supplier, drawable)
    }

    private fun canSaveItem(): Boolean{
        return  name.isNotEmpty() && price!=0 && quantity!=0 && supplier.isNotEmpty() && drawable!=0
    }

}