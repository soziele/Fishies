package com.example.fishies.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fishies.R
import com.example.fishies.model.Upgrade
import com.example.fishies.model.UpgradesList
import com.example.fishies.viewModel.FishDataViewModel
import com.example.fishies.viewModel.ShopListAdapter
import com.example.fishies.viewModel.StateViewModel

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
    private lateinit var stateViewModel: StateViewModel
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
        stateViewModel = ViewModelProvider(this).get(StateViewModel::class.java)
        list.value = UpgradesList.tackleBoxes
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
        tackleBoxesButton.setOnClickListener { list.value = UpgradesList.tackleBoxes}
        rodsButton.setOnClickListener { list.value = UpgradesList.rods }
        baitsButton.setOnClickListener { list.value = UpgradesList.baits }
        anglersButton.setOnClickListener { list.value = UpgradesList.anglers }
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