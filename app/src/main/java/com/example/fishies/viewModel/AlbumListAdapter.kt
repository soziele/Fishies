package com.example.fishies.viewModel

import android.graphics.Color
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.marginTop
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.fishies.R

class AlbumListAdapter (var items: LiveData<List<String>>): RecyclerView.Adapter<AlbumListAdapter.ItemsHolder>(){

    inner class ItemsHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fish_card, parent, false)

        return ItemsHolder(view)
    }

    override fun onBindViewHolder(holder: ItemsHolder, position: Int) {

        var fishName = holder.itemView.findViewById<TextView>(R.id.card_fish_name)
        var fishLatinoName = holder.itemView.findViewById<TextView>(R.id.card_fish_latino_name)
        var fishPhoto = holder.itemView.findViewById<ImageView>(R.id.card_fish_photo)
        var fishParameters = holder.itemView.findViewById<TextView>(R.id.card_fish_parameters)
        var fishDescription = holder.itemView.findViewById<TextView>(R.id.card_fish_description)
        var fishCardLayout = holder.itemView.findViewById<LinearLayout>(R.id.fish_card_layout)

        fishName.text = items.value!![position]

        if(items.value!![position] == "Not Unlocked Yet"){
            holder.itemView.setBackgroundColor(Color.LTGRAY)
            fishPhoto.setImageResource(R.drawable.locked)
            fishLatinoName.text = ""
            fishParameters.text = ""
            fishDescription.text = ""
        }


    }

    override fun getItemCount(): Int {
        return items.value?.size?:0
    }
}