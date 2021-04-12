package com.example.fishies.viewModel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.fishies.R

class ShopListAdapter (var items: LiveData<List<String>>): RecyclerView.Adapter<ShopListAdapter.ItemsHolder>(){

    inner class ItemsHolder(view: View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shop_shelf, parent, false)

        return ItemsHolder(view)
    }

    override fun onBindViewHolder(holder: ItemsHolder, position: Int) {

        val itemName = holder.itemView.findViewById<TextView>(R.id.item_name)
        itemName.text = items.value!![position]
    }

    override fun getItemCount(): Int {
        return items.value?.size?:0
    }
}