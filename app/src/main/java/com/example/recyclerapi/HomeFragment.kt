package com.example.recyclerapi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch



class HomeFragment : Fragment() {
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
        lifecycleScope.launch {
            loading?.visibility = View.VISIBLE
            val response = try{
                RetrofitInstance.api.getUserData()
            }catch (e: Exception){
                loading?.visibility = View.GONE
                return@launch
            }
            if (response.isSuccessful && response.body() != null){
                val usersRecyclerView = view?.findViewById<RecyclerView>(R.id.users_list_view)
                    ?.apply {
                        adapter = UserAdapter(response.body()!!.Results, {onItemClickListener(it)} )
                        layoutManager = LinearLayoutManager(this.context)

                        setHasFixedSize(true)
                    }
                loading?.visibility = View.GONE
            } else {
                loading?.visibility = View.GONE
            }
        }

        return inflater.inflate(R.layout.fragment_home, container, false)
    }
    fun onItemClickListener(carModel: User) {

        val fragment = CarFragment.newInstance(carModel)
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.fragmentContainer,fragment)
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