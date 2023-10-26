package com.example.recyclerapi.network


import com.example.recyclerapi.models.Cars
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("/api/vehicles/getallmanufacturers?format=json")
    suspend fun getUserData(@Query("page") page: Int): Cars
}