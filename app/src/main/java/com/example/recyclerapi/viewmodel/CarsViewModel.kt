package com.example.recyclerapi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recyclerapi.network.ApiInterface
import com.example.recyclerapi.network.RetrofitInstance
import com.example.recyclerapi.models.Cars
import kotlinx.coroutines.launch

class CarsViewModel : ViewModel() {
    var car:MutableLiveData<Cars> = MutableLiveData()

    fun getCar(page : Int){
        viewModelScope.launch {
            try {
                val retrofit = RetrofitInstance.getCarData()
                val service = retrofit.create(ApiInterface::class.java)
                val response = service.getUserData(page)
                car.value=response
            }
            catch (e:Exception){

            }
        }
    }
}