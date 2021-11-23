package com.android.mvpapp.view.adapter
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.android.inventory.R
import com.android.mvpapp.inflate
import com.android.mvpapp.model.Item
import com.android.mvpapp.view.item.ItemActivity

import kotlinx.android.synthetic.main.list_item_item.view.*
import java.io.ByteArrayOutputStream

class ItemAdapter(private val items: MutableList<Item>)
    : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_item_item, parent, false)
        val vh = ViewHolder(v)
        context = parent.context
        return vh
//        return ViewHolder(parent.inflate(R.layout.list_item_item))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])

    }

    override fun getItemCount() = items.size

    fun updateItems(items: List<Item>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private lateinit var item: Item

        fun bind(item: Item) {
            this.item = item
            itemView.avatarListItem.setImageBitmap(item.image)
            itemView.name.text = item.name
            itemView.price.text = item.price.toString() + " $"
            itemView.quantity.text = item.quantity.toString() + " pcs."
            itemView.supplier.text = "By: "+item.supplier

            itemView.setOnClickListener {

                val stream = ByteArrayOutputStream()
                item.image.compress(Bitmap.CompressFormat.PNG, 100, stream)
                val byteArray = stream.toByteArray()
                itemView.avatarListItem.setImageBitmap(item.image)

                val intent = Intent(it.context, ItemActivity::class.java)
                intent.putExtra("name", item.name)
                intent.putExtra("price", item.price.toString())
                intent.putExtra("image", byteArray)
                intent.putExtra("quantity", item.quantity.toString())
                intent.putExtra("supplier", item.supplier)
                it.context.startActivity(intent)
            }

        }




    }
}