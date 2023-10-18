package com.example.recyclerapi


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(private val listener: (User) -> Unit) :
    ListAdapter<User, UserAdapter.ViewHolder>(DiffUserCallBack()) {

    inner class ViewHolder(private val containerView: View) :
        RecyclerView.ViewHolder(containerView) {
        init {
            itemView.setOnClickListener {
                listener.invoke(getItem(adapterPosition))
            }
        }

        fun bind(user: User) {
            containerView.findViewById<TextView>(R.id.country).text = user.Country
//            containerView.findViewById<TextView>(R.id.commonName).text = user.Mfr_CommonName
//            containerView.findViewById<TextView>(R.id.mfrid).text = user.Mfr_ID
            containerView.findViewById<TextView>(R.id.mfrname).text = user.Mfr_Name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.user_layout, parent, false)
        return ViewHolder(itemLayout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class DiffUserCallBack : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.Country == newItem.Country
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }

}