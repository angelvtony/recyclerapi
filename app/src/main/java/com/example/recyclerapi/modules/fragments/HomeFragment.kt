package com.example.recyclerapi.modules.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerapi.viewmodel.CarsViewModel
import com.example.recyclerapi.R
import com.example.recyclerapi.network.UserAdapter
import com.example.recyclerapi.models.CarsList


class HomeFragment : Fragment() {
    private lateinit var carsViewModel: CarsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val loading = view?.findViewById<ProgressBar>(R.id.progress_bar)
        var viewModel = ViewModelProvider(this).get(CarsViewModel::class.java)

        viewModel.car.observe(viewLifecycleOwner){

            val usersRecyclerView = view?.findViewById<RecyclerView>(R.id.users_list_view)
                ?.apply {

                    layoutManager = LinearLayoutManager(this.context)
                    adapter = UserAdapter(it.Results,{ onItemClickListener(it) })
                    //setHasFixedSize(true)

                }

            loading?.visibility = View.GONE


        }


        viewModel.getCar()

        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    fun onItemClickListener(carModel: CarsList) {

        val fragment = CarFragment.newInstance(carModel)
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.fragmentContainer, fragment)
            ?.addToBackStack(null)
            ?.commit()
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}