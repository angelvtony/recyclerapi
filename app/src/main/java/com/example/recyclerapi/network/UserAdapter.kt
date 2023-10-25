package com.example.recyclerapi.network


import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerapi.R
import com.example.recyclerapi.models.CarsList

class UserAdapter(
    private var mList: List<CarsList>,
    private val clickListener: (carModel: CarsList) -> Unit
) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_layout, parent, false)


        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val carModel = mList[position]

        holder.titleTextView.text = carModel.Country
        holder.subTitleTextView.text = carModel.Mfr_Name

        holder.itemView.setOnClickListener {

            clickListener(carModel)

        }

    }

    override fun getItemCount(): Int {
        return mList.size
    }


    fun updateItems(newItems: List<CarsList>){

        mList= newItems
        Log.d("MLIST SIZE",mList.size.toString())
        notifyDataSetChanged()
    }


    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {


        val titleTextView: TextView = itemView.findViewById(R.id.country)
        val subTitleTextView: TextView = itemView.findViewById(R.id.mfrname)
    }


}