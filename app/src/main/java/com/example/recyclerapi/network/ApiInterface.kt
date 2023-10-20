package com.example.recyclerapi.network


import com.example.recyclerapi.models.Cars
import retrofit2.http.GET

interface ApiInterface {

    @GET("/api/vehicles/getallmanufacturers?format=json")
    suspend fun getUserData() : Cars
}