package com.example.fishies.viewModel

import android.graphics.Color
import android.transition.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fishies.R
import com.example.fishies.model.FishData

class AlbumListAdapter(var items: MutableList<FishData>?): RecyclerView.Adapter<AlbumListAdapter.ItemsHolder>(){

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

        fishCardLayout.alpha = 0f
        fishCardLayout.visibility = VISIBLE
        fishCardLayout.animate()
                .alpha(1f)
                .setDuration(1000)

        fishName.text = items!![position].name
        fishLatinoName.text = items!![position].scientific
        fishParameters.text = "Average length: "+items!![position].avLength.toString()
        fishDescription.text = items!![position].biology
        fishPhoto.setImageResource(R.drawable.fish)
        holder.itemView.setBackgroundColor(Color.parseColor("#E3F1F6"))

        if(!items!![position].unlocked!!){
            holder.itemView.setBackgroundColor(Color.LTGRAY)
            fishPhoto.setImageResource(R.drawable.locked)
            fishLatinoName.text = ""
            fishParameters.text = ""
            fishDescription.text = ""
        }


    }

    override fun getItemCount(): Int {
        return items?.size?:0
    }
}