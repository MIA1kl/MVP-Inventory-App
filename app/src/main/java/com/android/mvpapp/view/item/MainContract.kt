package com.android.mvpapp.view.item

import android.graphics.Bitmap


interface MainContract {

    interface Presenter {
        fun updateName(name: String)
        fun updatePrice(price: Int)
        fun updateQuantity(quantity: Int)
        fun updateSupplier(supplier: String)
        fun imageSelected(drawable: Bitmap)
        fun isDrawableSelected(): Boolean
        fun saveItem()
    }

    interface View {
        fun showAvatarBitmap(resourceId: Bitmap)
        fun showItemSaved()
        fun showItemSavedError()
    }

}