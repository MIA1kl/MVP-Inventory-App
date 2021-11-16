package com.android.mvpapp.presenter

import androidx.annotation.DrawableRes

interface MainContract {

    interface Presenter {
        fun updateName(name: String)
        fun updatePrice(price: Int)
        fun updateQuantity(quantity: Int)
        fun updateSupplier(supplier: String)
        fun drawableSelected(drawable: Int)
        fun isDrawableSelected(): Boolean
        fun saveItem()
    }

    interface View {
        fun showAvatarDrawable(@DrawableRes resourceId: Int)
        fun showItemSaved()
        fun showItemSavedError()
    }

}