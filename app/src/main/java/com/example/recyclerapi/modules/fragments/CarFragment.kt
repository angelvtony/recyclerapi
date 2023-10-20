package com.example.recyclerapi.modules.fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.recyclerapi.R
import com.example.recyclerapi.models.Result
import com.example.recyclerapi.models.CarsList


class CarFragment : Fragment() {
    private var carData: CarsList?=null
   private var para_name= "carmodels"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                carData = it.getParcelable(para_name, Result::class.java) as CarsList
            }else{
                carData = it.getParcelable<Result>(para_name) as CarsList
            }
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

        val textView :TextView=view.findViewById(R.id.txt)
        val textView2 :TextView=view.findViewById(R.id.txt1)
        val textView3 :TextView=view.findViewById(R.id.txt2)
        val textView4 :TextView=view.findViewById(R.id.txt3)
        val textView5 :TextView=view.findViewById(R.id.txt4)

        textView.text=carData?.Mfr_CommonName
        textView2.text=carData?.Mfr_ID
        textView3.text=carData?.Mfr_Name
        textView5.text=carData?.Country
        val vehicleTypes = carData?.VehicleTypes
        if (vehicleTypes != null) {
            var vehicleTypesString = ""
            for (vehicleType in vehicleTypes) {
                vehicleTypesString += vehicleType.Name + " "
            }
            textView4.text = vehicleTypesString
        }
    }
    companion object {
        @JvmStatic
        fun newInstance(carModel: CarsList) =
            CarFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(para_name,carModel)
                }
            }
    }
}