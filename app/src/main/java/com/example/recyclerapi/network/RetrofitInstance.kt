package com.example.recyclerapi.network


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    const val baseUrl = "https://vpic.nhtsa.dot.gov/"

    fun getCarData(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }
    val api : ApiInterface by lazy {
        Retrofit.Builder()
            .baseUrl("https://vpic.nhtsa.dot.gov/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
    }
}