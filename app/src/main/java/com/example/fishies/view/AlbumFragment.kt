package com.example.fishies.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fishies.R
import com.example.fishies.model.FishData
import com.example.fishies.repository.FishRepository
import com.example.fishies.repository.UserRepository
import com.example.fishies.viewModel.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [upgrades.newInstance] factory method to
 * create an instance of this fragment.
 */
class AlbumFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var myadapter: AlbumListAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var myLayoutManager: LinearLayoutManager

    private lateinit var fishDataVM: FishDataViewModel
    private lateinit var stateViewModel: StateViewModel
    var unlockedFishNumber = 0

    var list = MutableLiveData<List<String>>()

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

        val userRepository = UserRepository()
        val stateViewModelFactory = StateViewModelFactory(userRepository)
        stateViewModel = ViewModelProvider(requireActivity(), stateViewModelFactory).get(StateViewModel::class.java)

        val repository = FishRepository()
        val viewModelFactory = FishDataViewModelFactory(repository)
        fishDataVM = ViewModelProvider(requireActivity(), viewModelFactory).get(FishDataViewModel::class.java)

        stateViewModel.User.observe(viewLifecycleOwner, Observer{ user->
            unlockedFishNumber = user.lastUnlockedFish
        })

        myLayoutManager = GridLayoutManager(context, 2)
        setAdapter(mutableListOf())

        myadapter = AlbumListAdapter(fishDataVM.fishList.value, requireActivity(), unlockedFishNumber)

        fishDataVM.fishList.observe(viewLifecycleOwner, Observer{list->
            for(index in 0..unlockedFishNumber){
                list[index].unlocked = true
            }
            myadapter.notifyDataSetChanged()
            recyclerView.adapter = AlbumListAdapter(list, requireActivity(), unlockedFishNumber)
        })


        return inflater.inflate(R.layout.fragment_album, container, false)
    }

    fun setAdapter(fishList: MutableList<FishData>){
        myadapter = AlbumListAdapter(fishList, requireActivity(), unlockedFishNumber)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        recyclerView = view.findViewById<RecyclerView>(R.id.fish_collection_list).apply {
            this.layoutManager = myLayoutManager
            this.adapter = myadapter
        }

        var exitAlbumButton = view.findViewById<ImageButton>(R.id.exit_album_button)
        exitAlbumButton.setOnClickListener{ view.findNavController().navigate(R.id.action_quests_to_game)}

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment upgrades.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AlbumFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}