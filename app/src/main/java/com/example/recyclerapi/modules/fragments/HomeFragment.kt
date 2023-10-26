package com.example.recyclerapi.modules.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerapi.viewmodel.CarsViewModel
import com.example.recyclerapi.R
import com.example.recyclerapi.network.UserAdapter
import com.example.recyclerapi.models.CarsList
import android.os.Handler
import android.util.Log
import android.widget.ProgressBar

class HomeFragment : Fragment() {
    private lateinit var viewModel: CarsViewModel
    private var tempDataList: List<CarsList> = listOf()
    private val pageSize = 50
    private lateinit var userAdapter: UserAdapter
    private var progress_bar: ProgressBar? = null
    private var handler = Handler()
    private var isScrolling = false
    private var currentPage = 1

    private var isFirstTime: Boolean = true
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)
        progress_bar = view.findViewById<ProgressBar>(R.id.progress_bar)
        viewModel = ViewModelProvider(this).get(CarsViewModel::class.java)

        val usersRecyclerView = view.findViewById<RecyclerView>(R.id.users_list_view)

        progress_bar!!.visibility = View.VISIBLE
        viewModel.car.observe(viewLifecycleOwner) { carsResponse ->
            tempDataList += carsResponse.Results.subList(
                0,
                minOf(pageSize, carsResponse.Results.size)
            )

            if (isFirstTime) {

                userAdapter = UserAdapter(tempDataList) { onItemClickListener(it) }
                usersRecyclerView?.layoutManager = LinearLayoutManager(this.context)
                usersRecyclerView?.adapter = userAdapter
                isFirstTime = false

            } else {

                userAdapter.updateItems(tempDataList)
            }

            progress_bar!!.visibility = View.GONE
        }

        usersRecyclerView?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

                if (isScrolling && (visibleItemCount + firstVisibleItem) >= totalItemCount) {
                    isScrolling = false
//                    loadMoreItems()
                    progress_bar!!.visibility = View.VISIBLE
                    viewModel.getCar(++currentPage)

                }
            }
        })

        viewModel.getCar(currentPage)

        return view
    }

    fun onItemClickListener(carModel: CarsList) {
        val fragment = CarFragment.newInstance(carModel)
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.fragmentContainer, fragment)
            ?.addToBackStack(null)
            ?.commit()
    }
}
