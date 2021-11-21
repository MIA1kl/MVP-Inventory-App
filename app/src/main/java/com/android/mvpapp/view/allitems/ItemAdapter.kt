package com.android.mvpapp.view.allitems
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.android.inventory.R
import com.android.mvpapp.inflate
import com.android.mvpapp.model.Item

import kotlinx.android.synthetic.main.list_item_item.view.*

class ItemAdapter(private val items: MutableList<Item>)
    : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.list_item_item))
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

        }
    }
}