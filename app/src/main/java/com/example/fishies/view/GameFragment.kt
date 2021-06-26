package com.example.fishies.view

import android.annotation.SuppressLint
import android.content.res.Resources
import android.content.res.loader.ResourcesLoader
import android.content.res.loader.ResourcesProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.fishies.R
import com.example.fishies.model.LocationsList
import com.example.fishies.model.UpgradesList
import com.example.fishies.repository.FishRepository
import com.example.fishies.repository.UserRepository
import com.example.fishies.viewModel.FishDataViewModel
import com.example.fishies.viewModel.FishDataViewModelFactory
import com.example.fishies.viewModel.StateViewModel
import com.example.fishies.viewModel.StateViewModelFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Game.newInstance] factory method to
 * create an instance of this fragment.
 */
class Game : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var stateViewModel: StateViewModel
    private lateinit var fishDataVM: FishDataViewModel
    private var unlockedBaitNumber = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val userRepository = UserRepository()
        val stateViewModelFactory = StateViewModelFactory(userRepository)
        stateViewModel = ViewModelProvider(requireActivity(), stateViewModelFactory).get(StateViewModel::class.java)

        val repository = FishRepository()
        val viewModelFactory = FishDataViewModelFactory(repository)
        fishDataVM = ViewModelProvider(requireActivity(), viewModelFactory).get(FishDataViewModel::class.java)

        for(bait in UpgradesList.baits){
            if(bait.bought) unlockedBaitNumber++
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val shopButton = view.findViewById<Button>(R.id.shop_button)
        val questsButton = view.findViewById<Button>(R.id.quests_button)
        val mapButton = view.findViewById<Button>(R.id.map_button)
        shopButton.setOnClickListener { view.findNavController().navigate(R.id.action_game_to_shop) }
        questsButton.setOnClickListener { view.findNavController().navigate(R.id.action_game_to_quests) }
        mapButton.setOnClickListener { view.findNavController().navigate(R.id.action_game_to_mapFragment) }

        val gameScreen = view.findViewById<ImageView>(R.id.game_screen)
        val gameFrame = view.findViewById<FrameLayout>(R.id.game_frame)

        stateViewModel.fishesNumber.observe(viewLifecycleOwner, Observer { number->
            stateViewModel.User.observe(viewLifecycleOwner, Observer {user->
                fishDataVM.fishList.observe(viewLifecycleOwner, Observer{fishList->

                gameScreen.setImageResource(LocationsList.locations.last {location -> location.bought }.background)

        gameScreen.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> {
                        if (number >= UpgradesList.tackleBoxes.get(user.tackleBox).value) {
                            Toast.makeText(context, "Your tackle box won't contain that many fish!", Toast.LENGTH_SHORT).show()
                        } else {
                            stateViewModel.click()
                            var randomFish = (0..unlockedBaitNumber).random()

                            if(!fishList[randomFish].unlocked!! && randomFish > user.lastUnlockedFish){
                                Toast.makeText(context, "New fish caught!", Toast.LENGTH_SHORT).show()
                                fishList[randomFish].unlocked = true
                                user.lastUnlockedFish = randomFish
                            }

                            var newFish: ImageView
                            newFish = ImageView(context)
                            newFish.setImageResource(resources.getIdentifier("fish${randomFish+1}", "drawable", requireActivity().packageName))
                            gameFrame.addView(newFish)
                            newFish.x = event.x
                            newFish.y = event.y
                            newFish.layoutParams.height = 100
                            newFish.layoutParams.width = 100
                            newFish.startAnimation(AnimationUtils.loadAnimation(context, R.anim.pop_out))
                            newFish.visibility = View.INVISIBLE
                        }
                    }
                }

                return v?.onTouchEvent(event) ?: true
            }
                })
            })
            })
        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment game.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                Game().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}