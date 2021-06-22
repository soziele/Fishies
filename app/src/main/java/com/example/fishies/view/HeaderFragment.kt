package com.example.fishies.view

import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.fishies.R
import com.example.fishies.repository.FishRepository
import com.example.fishies.repository.UserRepository
import com.example.fishies.viewModel.FishDataViewModel
import com.example.fishies.viewModel.FishDataViewModelFactory
import com.example.fishies.viewModel.StateViewModel
import com.example.fishies.viewModel.StateViewModelFactory
import okhttp3.internal.notifyAll

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HeaderFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HeaderFragment : Fragment() {
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
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val repository = UserRepository()
        val viewModelFactory = StateViewModelFactory(repository)
        stateViewModel = ViewModelProvider(this, viewModelFactory).get(StateViewModel::class.java)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_header, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // initialize in game displayed values
        val moneyHeader = view.findViewById<TextView>(R.id.money_header)
        val fishHeader = view.findViewById<TextView>(R.id.fishes_header)

        // attach observers to both value to update them

        stateViewModel.User.observe(viewLifecycleOwner, Observer { t ->
            moneyHeader.text = t.money.toString()
            fishHeader.text = t.fishes.toString()

            val sellButton = view.findViewById<Button>(R.id.sell_button)
            sellButton.text = Html.fromHtml("<b><medium>" + "SELL" + "</medium></b>" +  "<br />" +
                    "<small><small>" + "1$ per fish" + "</small></small>")

            sellButton.setOnClickListener {
                stateViewModel.sellFishes()
                fishHeader.text = t.fishes.toString()
                moneyHeader.text = t.money.toString()
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
         * @return A new instance of fragment HeaderFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HeaderFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}