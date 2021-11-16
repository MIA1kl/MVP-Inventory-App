package com.android.mvpapp.model


class ItemGenerator {

    fun generateItem(name: String = "", price: Int = 0, quantity : Int = 0, supplier: String = "", drawable: Int = 0): Item {
        return Item(name,price,quantity,supplier, drawable)
    }

}