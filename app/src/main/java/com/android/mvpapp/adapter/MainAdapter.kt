package com.android.mvpapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.android.mvpapp.R
import com.android.mvpapp.model.ItemModel

class MainAdapter(var context : Context): RecyclerView.Adapter<MainAdapter.Holder>() {

    var list = arrayListOf<ItemModel>()

    fun setItem(items: ArrayList<ItemModel>){
        this.list.clear()
        this.list.addAll(items)
        notifyDataSetChanged()
    }

    fun addItem(item:ItemModel){
        list.add(item)
        notifyDataSetChanged()
    }

    fun deleteItem(position: Int){
        list.removeAt(position)
        notifyItemRemoved(position)
        notifyDataSetChanged()
    }

    inner class Holder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val item = itemView.findViewById<AppCompatTextView>(R.id.cardNameTextView)
        val btn_add = itemView.findViewById<AppCompatImageButton>(R.id.btn_add)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_card,parent,false)
        return Holder(itemView)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.item.text = list[position].name
    }

    override fun getItemCount(): Int = list.size
}