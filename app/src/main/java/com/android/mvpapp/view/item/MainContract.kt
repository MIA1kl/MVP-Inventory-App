package com.android.mvpapp.view.item

import android.graphics.Bitmap
import com.android.mvpapp.model.Item


interface MainContract {

    interface Presenter {
        fun updateName(name: String)
        fun updatePrice(price: Int)
        fun updateQuantity(quantity: Int)
        fun updateSupplier(supplier: String)
        fun imageSelected(image: Bitmap)
        fun isImageSelected(): Boolean
        fun saveItem()
        fun updateThisItem(item: Item)
        fun deleteItem(name: String)
    }

    interface View {
        fun showAvatarBitmap(resourceId: Bitmap)
        fun showItemSaved()
        fun showItemSavedError()
        fun checkIntent()
    }

}