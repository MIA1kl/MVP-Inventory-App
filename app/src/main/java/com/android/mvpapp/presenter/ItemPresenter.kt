package com.android.mvpapp.presenter

import android.graphics.Bitmap
import com.android.mvpapp.model.Item
import com.android.mvpapp.model.ItemGenerator
import com.android.mvpapp.model.room.RoomRepository
import com.android.mvpapp.view.item.MainContract


class ItemPresenter(private val itemGenerator: ItemGenerator = ItemGenerator(),
                    private val repository: RoomRepository = RoomRepository()) :
    BasePresenter<MainContract.View>(), MainContract.Presenter {

    private lateinit var item: Item

    private var name = ""
    private var price = 0
    private var quantity = 0
    private var supplier = ""
    private var image  = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888)

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


    override fun imageSelected(bitmap: Bitmap) {
        this.image = bitmap
        getView()?.showAvatarBitmap(bitmap)
        updateItem()
    }

    override fun isImageSelected(): Boolean {
        return !(image.sameAs(Bitmap.createBitmap(image.getWidth(), image.getHeight(), image.getConfig())))
    }

    override fun saveItem() {
        if (canSaveItem()) {
            repository.saveItem(item)
            getView()?.showItemSaved()
        } else {
            getView()?.showItemSavedError()
        }
    }

    override fun updateThisItem(item: Item) {
//        if (canSaveItem()) {
            repository.updateThisItem(item)
            updateItem()
            getView()?.showItemSaved()
//        } else {
//            getView()?.showItemSavedError()
//        }
    }

    override fun deleteItem(name: String) {
        repository.deleteItem(name)
    }

    private fun updateItem() {
        item = itemGenerator.generateItem( name,price,quantity,supplier, image)
    }

    private fun canSaveItem(): Boolean{
        return  name.isNotEmpty() && price!=0 && quantity!=0 && supplier.isNotEmpty() && !(image.sameAs(Bitmap.createBitmap(image.getWidth(), image.getHeight(), image.getConfig())))
    }


}