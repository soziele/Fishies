package com.example.fishies.view

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.fishies.R
import com.example.fishies.model.Location
import com.example.fishies.model.LocationsList
import com.example.fishies.repository.UserRepository
import com.example.fishies.viewModel.StateViewModel
import com.example.fishies.viewModel.StateViewModelFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MapFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MapFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var stateViewModel: StateViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        val userRepository = UserRepository()
        val stateViewModelFactory = StateViewModelFactory(userRepository)
        stateViewModel = ViewModelProvider(requireActivity(), stateViewModelFactory).get(StateViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pondPin = view.findViewById<ImageButton>(R.id.pond_pin)
        val springsPin = view.findViewById<ImageButton>(R.id.springs_pin)
        val islePin = view.findViewById<ImageButton>(R.id.isle_pin)
        val waterfallPin = view.findViewById<ImageButton>(R.id.waterfall_pin)
        val bayPin = view.findViewById<ImageButton>(R.id.bay_pin)

        val pondPrice = view.findViewById<TextView>(R.id.pond_price)
        val springsPrice = view.findViewById<TextView>(R.id.springs_price)
        val islePrice = view.findViewById<TextView>(R.id.isle_price)
        val waterfallPrice = view.findViewById<TextView>(R.id.waterfall_price)
        val bayPrice = view.findViewById<TextView>(R.id.bay_price)

        val pinsList = listOf(pondPin, springsPin, islePin, waterfallPin, bayPin)
        val pricesList = listOf(pondPrice, springsPrice, islePrice, waterfallPrice, bayPrice)

        for(pin in pinsList){
            setBuyOnClick(pin, pricesList[pinsList.indexOf(pin)], pinsList.indexOf(pin))
            if(LocationsList.locations[pinsList.indexOf(pin)].bought){
                pin.setImageResource(R.drawable.visited)
                pin.isEnabled = false
            }else{
                pin.setImageResource(R.drawable.unvisited)
            }
        }


        for(price in pricesList){
            if(LocationsList.locations[pricesList.indexOf(price)].bought){
                price.text = LocationsList.locations[pricesList.indexOf(price)].name
            }
            else {
                price.text = LocationsList.locations[pricesList.indexOf(price)].price.toString()+"$"
            }
        }


        val exitMapButton = view.findViewById<ImageButton>(R.id.exit_map_button)
        exitMapButton.setOnClickListener { view.findNavController().navigate(R.id.action_mapFragment_to_game) }
    }

    fun setBuyOnClick(pin: ImageButton, price: TextView, index: Int){
        stateViewModel.User.observe(viewLifecycleOwner, Observer{user->

            pin.setOnClickListener {
                if(user.money < LocationsList.locations[index].price){
                    Toast.makeText(context, "You don't have enough money!", Toast.LENGTH_SHORT).show()
                }
                else if(LocationsList.locations.indexOf(LocationsList.locations.last { location -> location.bought }) < index - 1) {
                    Toast.makeText(context, "You can't skip the previous location!", Toast.LENGTH_SHORT).show()
                }else {
                    stateViewModel.buyLocation(index)
                    pin.setImageResource(R.drawable.visited)
                    price.text = LocationsList.locations[index].name


                    val dialogBuilder = AlertDialog.Builder(requireActivity())
                    dialogBuilder.setMessage(it.toString())
                        // if the dialog is cancelable
                        .setCancelable(false)
                        .setPositiveButton("Ok", DialogInterface.OnClickListener {
                                dialog, id ->
                            dialog.dismiss()

                        })

                    val alert = dialogBuilder.create()
                    alert.setTitle("${LocationsList.locations[index].name} unlocked!")
                    alert.setMessage("Congratulations, you unlocked new location!\nNew fish species can be found here.")
                    alert.setIcon(R.drawable.location_icon)
                    alert.show()
                }
            }
        })
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MapFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MapFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}