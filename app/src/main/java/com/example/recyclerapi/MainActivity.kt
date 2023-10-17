package com.example.recyclerapi


import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val loading = findViewById<ProgressBar>(R.id.progress_bar)
        lifecycleScope.launch {
            loading.visibility = View.VISIBLE
            val response = try{
                RetrofitInstance.api.getUserData()
            }catch (e: Exception){
                loading.visibility = View.GONE
                return@launch
            }
                if (response.isSuccessful && response.body() != null){
                    val usersRecyclerView = findViewById<RecyclerView>(R.id.users_list_view).apply {
                    adapter = UserAdapter(){it}
                    layoutManager = LinearLayoutManager(this@MainActivity)
                    setHasFixedSize(true)
                }
                (usersRecyclerView.adapter as UserAdapter).submitList(response.body()!!.Results)
                loading.visibility = View.GONE
            } else {
                loading.visibility = View.GONE
            }
        }
    }
}