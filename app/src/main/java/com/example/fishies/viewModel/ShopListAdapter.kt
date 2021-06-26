package com.example.fishies.viewModel

import android.app.Application
import android.content.Context
import android.graphics.Color
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.fishies.R
import com.example.fishies.model.LocationsList
import com.example.fishies.model.Upgrade

class ShopListAdapter (var items: LiveData<List<Upgrade>>, val stateViewModel: StateViewModel, val context: Context): RecyclerView.Adapter<ShopListAdapter.ItemsHolder>(){

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
                itemDescription.text = "Works for you when you're away with the rate of "+items.value!![position].value+" fish per second"
            }
        }

        if(items.value!![position].bought){
            buyButton.text = Html.fromHtml(
                "<small><small><b>" + "Bought" + "</b></small></small>")
            buyButton.isEnabled = false
            buyButton.setBackgroundColor(Color.rgb(0, 171, 94))
            holder.itemView.setBackgroundColor(Color.rgb(211, 227, 216))
        }else {
            buyButton.text = Html.fromHtml(
                "<b><medium>" + "BUY" + "</medium></b>" + "<br />" +
                        "<small><small>" + "For " + items.value!![position].price + "\$" + "</small></small>"
            )
            buyButton.isEnabled = true
            buyButton.setBackgroundColor(Color.rgb(75, 5, 135))
            holder.itemView.setBackgroundColor(Color.WHITE)
        }
        buyButton.setOnClickListener {
            Log.d("Buying", "${items.value!![position].value+1/5}")
            Log.d("Buying", "${LocationsList.locations.indexOf(LocationsList.locations.last { location -> location.bought })+1}")
            if(items.value!![position].price > stateViewModel.User.value!!.money){
                Toast.makeText(context, "You don't have enough money!", Toast.LENGTH_SHORT).show()
            }else if(items.value!![position]. type == "Bait" && ((items.value!![position].value)/5) >= (LocationsList.locations.indexOf(LocationsList.locations.last { location -> location.bought })+1)) {
                Toast.makeText(context, "This type of fish doesn't exist in this location!", Toast.LENGTH_SHORT).show()
            }else if(items.value!![position]. type == "Bait" && position!=0 && !items.value!![position-1].bought) {
                Toast.makeText(context, "You need to catch ${items.value!![position-1].name.substring(0,items.value!![position-1].name.length-5)} first!", Toast.LENGTH_SHORT).show()
            }else {
                stateViewModel.buyItem(items.value!![position])
            }
            }
        }

    override fun getItemCount(): Int {
        return items.value?.size?:0
    }
}