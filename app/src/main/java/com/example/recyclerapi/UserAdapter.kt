package com.example.recyclerapi


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(private val mList: List<User>, private val clickListener: (carModel:User)->Unit) : RecyclerView.Adapter<UserAdapter.ViewHolder>(){

//    inner class ViewHolder(private val containerView : View) : RecyclerView.ViewHolder(containerView){
//        init {
//            itemView.setOnClickListener {
//                listener.invoke(getItem(adapterPosition))
//            }
//        }
//
//        fun bind(user : User){
//            containerView.findViewById<TextView>(R.id.country).text = user.Country
////            containerView.findViewById<TextView>(R.id.commonName).text = user.Mfr_CommonName
////            containerView.findViewById<TextView>(R.id.mfrid).text = user.Mfr_ID
//            containerView.findViewById<TextView>(R.id.mfrname).text = user.Mfr_Name
//        }
//    }
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val itemLayout = LayoutInflater.from(parent.context).inflate(R.layout.user_layout, parent, false)
//        return ViewHolder(itemLayout)
//    }
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.bind(getItem(position))
//    }
//}
//class DiffUserCallBack : DiffUtil.ItemCallback<User>(){
//    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
//        return oldItem.Mfr_ID == newItem.Mfr_ID
//    }
//    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
//        return oldItem == newItem
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_layout, parent, false)


        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val carModel = mList[position]

        holder.titleTextView.text = carModel.Country
        holder.subTitleTextView.text =carModel.Mfr_Name

        holder.itemView.setOnClickListener{

            clickListener(carModel)

        }

    }
    override fun getItemCount(): Int {
        return mList.size
    }




    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {


        val titleTextView: TextView = itemView.findViewById(R.id.country)
        val subTitleTextView:TextView= itemView.findViewById(R.id.mfrname)
    }


}