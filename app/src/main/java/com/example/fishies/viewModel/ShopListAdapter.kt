package com.example.fishies.viewModel

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.fishies.R
import com.example.fishies.model.Upgrade

class ShopListAdapter (var items: LiveData<List<Upgrade>>): RecyclerView.Adapter<ShopListAdapter.ItemsHolder>(){

    inner class ItemsHolder(view: View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shop_shelf, parent, false)

        return ItemsHolder(view)
    }

    override fun onBindViewHolder(holder: ItemsHolder, position: Int) {

        val itemName = holder.itemView.findViewById<TextView>(R.id.item_name)
        val buyButton = holder.itemView.findViewById<Button>(R.id.buy_button)
        val itemIcon = holder.itemView.findViewById<ImageView>(R.id.item_icon)
        val itemDescription = holder.itemView.findViewById<TextView>(R.id.item_description)

        itemName.text = items.value!![position].name


        when(items.value!![0].type){
            "TackleBox"->{
                itemIcon.setImageResource(R.drawable.tacklebox)
                itemDescription.text = "Increases fish storage capacity to "+items.value!![position].value
            }
            "Rod"-> {
                itemIcon.setImageResource(R.drawable.rod)
                itemDescription.text = "Makes caught fish "+items.value!![position].value+" times more valuable"
            }
            "Bait"-> {
                itemIcon.setImageResource(R.drawable.bait)
                itemDescription.text = "Lets you catch specific type of fish"
            }
            "Angler"-> {
                itemIcon.setImageResource(R.drawable.fisherman)
                itemDescription.text = "Works for you for "+items.value!![position].value+" minutes"
            }
        }

        buyButton.text = Html.fromHtml("<b><medium>" + "BUY" + "</medium></b>" +  "<br />" +
                "<small><small>" + "For "+items.value!![position].price+"\$" + "</small></small>")
    }

    override fun getItemCount(): Int {
        return items.value?.size?:0
    }
}