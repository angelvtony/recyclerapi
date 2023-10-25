package com.example.recyclerapi.modules.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerapi.viewmodel.CarsViewModel
import com.example.recyclerapi.R
import com.example.recyclerapi.network.UserAdapter
import com.example.recyclerapi.models.CarsList


class HomeFragment : Fragment() {

    private lateinit var originalDataList:List<CarsList>
    private lateinit var tempDataList: List<CarsList>

    private val pageSize:Int =6

    private lateinit var adapter:UserAdapter


    private var isScrolling=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       // val loading = view?.findViewById<ProgressBar>(R.id.progress_bar)
        val viewModel = ViewModelProvider(this).get(CarsViewModel::class.java)

        val usersRecyclerView = view?.findViewById<RecyclerView>(R.id.users_list_view)

        viewModel.car.observe(viewLifecycleOwner){

            originalDataList = it.Results
            tempDataList= originalDataList.subList(0, minOf(pageSize,originalDataList.size))

            adapter =UserAdapter(it.Results,{ onItemClickListener(it) })

             usersRecyclerView
                 ?.apply {

                     layoutManager = LinearLayoutManager(this.context)

                     //setHasFixedSize(true)

                 }!!

            usersRecyclerView.adapter = adapter

//            loading?.visibility = View.GONE


        }

        usersRecyclerView?.addOnScrollListener(object : RecyclerView.OnScrollListener(){

            val layoutManager = LinearLayoutManager(requireContext())

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if(newState== AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){

                    isScrolling=true
                }

            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

                if( (visibleItemCount + firstVisibleItem)>=totalItemCount){

                    isScrolling=false
                    loadMoreItems()
                }

            }
        })


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


    fun loadMoreItems(){

        var pageStart = tempDataList.size
        var pageEnd= minOf(pageStart+pageSize, originalDataList.size)

        tempDataList+=originalDataList.subList(pageStart,pageEnd)

        adapter.updateItems(tempDataList)


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