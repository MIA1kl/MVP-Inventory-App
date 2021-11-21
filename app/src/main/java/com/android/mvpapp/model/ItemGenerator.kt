package com.android.mvpapp.model

import android.graphics.Bitmap


class ItemGenerator {

    fun generateItem(name: String = "", price: Int = 0, quantity: Int = 0, supplier: String = "", image: Bitmap= Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888)): Item {
        return Item(name,price,quantity,supplier, image)
    }

}