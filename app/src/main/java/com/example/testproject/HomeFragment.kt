package com.example.testproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testproject.databinding.FragmentHomeBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentHomeBinding

    private lateinit var carAdapter: CarsAdapter
    private lateinit var carArrayList : ArrayList<Car>

    lateinit var imageId : Array<Int>
    lateinit var titleName : Array<String>
    lateinit var titleYear : Array<String>

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
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataInitialize()
        setupHomeRecyclerView()
    }

    private fun setupHomeRecyclerView() {
        val mlayoutManager = LinearLayoutManager(context)
        mlayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.recyclerViewCars.apply {
            carAdapter = CarsAdapter(carArrayList, object : CarsAdapter.CarsItemListener{
                override fun onItemClick(car: Car) {
                    navigateToDetailFragment(car)
                }
            })
            adapter = carAdapter
            layoutManager = mlayoutManager
        }
    }

    private fun navigateToDetailFragment(car: Car) {
        println("car $car")
        val bundle = Bundle()
        bundle.putSerializable("key", car)
        findNavController().navigate(R.id.action_nav_home_to_nav_detail, bundle)
    }

    private fun dataInitialize(){
        carArrayList = arrayListOf<Car>()

        imageId = arrayOf(
            R.drawable.car,
            R.drawable.car2,
            R.drawable.car3,
            R.drawable.car4,
            R.drawable.car5,
            R.drawable.car6,
            R.drawable.car7
        )

        titleName = arrayOf(
            getString(R.string.title_1),
            getString(R.string.title_2),
            getString(R.string.title_3),
            getString(R.string.title_4),
            getString(R.string.title_5),
            getString(R.string.title_6),
            getString(R.string.title_7)
        )

        titleYear = arrayOf(
            getString(R.string.year_1),
            getString(R.string.year_2),
            getString(R.string.year_3),
            getString(R.string.year_4),
            getString(R.string.year_5),
            getString(R.string.year_6),
            getString(R.string.year_7)
        )

        for (i in imageId.indices){

            val car = Car(imageId[i], titleName[i], titleYear[i])
            carArrayList.add(car)
        }

    }
}


