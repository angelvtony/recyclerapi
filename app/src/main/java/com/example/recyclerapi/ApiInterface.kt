package com.example.recyclerapi


import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("/api/vehicles/getallmanufacturers?format=json")
    suspend fun getUserData() : Response<Cars>
}