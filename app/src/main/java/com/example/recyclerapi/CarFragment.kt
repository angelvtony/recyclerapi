package com.example.recyclerapi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.recyclerapi.models.Result



class CarFragment : Fragment() {
    private var carData:User?=null
   private var para_name= "carmodels"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            carData = it.getSerializable(para_name) as User

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_car, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var textView :TextView=view.findViewById(R.id.txt)
        var textView2 :TextView=view.findViewById(R.id.txt1)
        var textView3 :TextView=view.findViewById(R.id.txt2)

        textView.text=carData?.Mfr_CommonName
        textView2.text=carData?.Mfr_ID
        textView3.text=carData?.Mfr_Name
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(carModel:User) =
            CarFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(para_name,carModel)
                }
            }
    }
}