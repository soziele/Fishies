package com.example.fishies.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fishies.R
import com.example.fishies.model.Upgrade
import com.example.fishies.viewModel.ShopListAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [shop.newInstance] factory method to
 * create an instance of this fragment.
 */
class shop : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var myadapter: ShopListAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var myLayoutManager: LinearLayoutManager
    var list = MutableLiveData<List<Upgrade>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        list.value = listOf(
                Upgrade("TackleBox", "Tiny Box", "", 100,-1,200,false),
                Upgrade("TackleBox", "Small Box", "", 500,-1,800,false),
                Upgrade("TackleBox", "Medium Box", "", 2500,-1,1500,false),
                Upgrade("TackleBox", "Big Box", "", 5000,-1,10000,false),
                Upgrade("TackleBox", "Large Box", "", 10000,-1,50000,false))
        myadapter = ShopListAdapter(list)
        myLayoutManager = LinearLayoutManager(context)

        list.observe(viewLifecycleOwner, Observer{
            myadapter.notifyDataSetChanged()
        })
        return inflater.inflate(R.layout.fragment_shop, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById<RecyclerView>(R.id.shop_items_list).apply {
            this.layoutManager = myLayoutManager
            this.adapter = myadapter
        }

        val exitShopButton = view.findViewById<ImageButton>(R.id.exit_shop_button)
        val tackleBoxesButton = view.findViewById<ImageButton>(R.id.tackle_boxes_button)
        val rodsButton = view.findViewById<ImageButton>(R.id.rods_button)
        val baitsButton = view.findViewById<ImageButton>(R.id.baits_button)
        val anglersButton = view.findViewById<ImageButton>(R.id.anglers_button)

        exitShopButton.setOnClickListener { view.findNavController().navigate(R.id.action_shop_to_game) }
        tackleBoxesButton.setOnClickListener { list.value = listOf(
                Upgrade("TackleBox", "Tiny Box", "", 100,-1,200,false),
                Upgrade("TackleBox", "Small Box", "", 500,-1,800,false),
                Upgrade("TackleBox", "Medium Box", "", 2500,-1,1500,false),
                Upgrade("TackleBox", "Big Box", "", 5000,-1,10000,false),
                Upgrade("TackleBox", "Large Box", "", 10000,-1,50000,false)
      )}
        rodsButton.setOnClickListener { list.value = listOf(
                Upgrade("Rod","Toy Rod", "",1,-1,500,false),
                Upgrade("Rod","Plastic Rod", "",2,-1,1500,false),
                Upgrade("Rod","Bent Rod", "",3,-1,5000,false),
                Upgrade("Rod","Short Rod", "",4,-1,25000,false),
                Upgrade("Rod","Wooden Rod", "",5,-1,75000,false),
                Upgrade("Rod","Steel Rod", "",6,-1,150000,false),
                Upgrade("Rod","Long Steel Rod", "",7,-1,500000,false),
                Upgrade("Rod","Ornate Rod", "",10,-1,1500000,false),
                Upgrade("Rod","Golden Rod", "",15,-1,5000000,false),
                Upgrade("Rod","Ultimate Rod", "",20,-1,15000000,false)
        ) }
        baitsButton.setOnClickListener { list.value = listOf(
                Upgrade("Bait","Walleye Bait", "",0,0,5,false),
                Upgrade("Bait","Northern Pike Bait", "",1,0,10,false),
                Upgrade("Bait","Bluegill Bait", "",2,0,25,false),
                Upgrade("Bait","Green Sunfish Bait", "",3,0,50,false),
                Upgrade("Bait","Brook Trout Bait", "",4,0,75,false),
                Upgrade("Bait","Bool Trout Bait", "",5,0,100,false),
                Upgrade("Bait","Rainbow Trout Bait", "",6,0,150,false),
                Upgrade("Bait","Common Carp Bait", "",7,0,200,false),
                Upgrade("Bait","Fathead Minnow Bait", "",8,0,250,false),
                Upgrade("Bait","Channel Catfish Bait", "",9,0,300,false),
                Upgrade("Bait","Flathead Catfish Bait", "",10,0,350,false),
                Upgrade("Bait","Lake Chub Bait", "",11,0,400,false),
                Upgrade("Bait","Longnose Gar Bait", "",12,0,450,false),
                Upgrade("Bait","Spotted Gar Bait", "",13,0,500,false),
                Upgrade("Bait","Muskellunge Bait", "",14,0,550,false),
                Upgrade("Bait","Chain Pickerel Bait", "",15,0,600,false),
                Upgrade("Bait","Pumpkinseed Bait", "",16,0,650,false),
                Upgrade("Bait","Largemouth Bass Bait", "",17,0,700,false),
                Upgrade("Bait","Smallmouth Bass Bait", "",18,0,750,false),
                Upgrade("Bait","Small Bass Bait", "",19,0,800,false),
                Upgrade("Bait","Ruffe Bait", "",20,0,850,false),
                Upgrade("Bait","White Sucker Bait", "",21,0,900,false),
                Upgrade("Bait","Yellow Perch Bait", "",22,0,950,false),
                Upgrade("Bait","Bowfin Bait", "",23,0,1000,false),
                Upgrade("Bait","Lake Sturgeon Bait", "",24,0,2000,false)
        ) }
        anglersButton.setOnClickListener { list.value = listOf(
                Upgrade("Angler","John", "",5,-1,5000,false),
                Upgrade("Angler","Henry", "",15,-1,25000,false),
                Upgrade("Angler","Andrew", "",30,-1,50000,false),
                Upgrade("Angler","Maria", "",60,-1,100000,false),
                Upgrade("Angler","Peter", "",90,-1,200000,false),
                Upgrade("Angler","Gill", "",120,-1,300000,false),
                Upgrade("Angler","Tom", "",180,-1,400000,false),
                Upgrade("Angler","Jack", "",300,-1,500000,false),
        ) }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment shop.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            shop().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}