package com.example.recyclerapi

data class Cars(val Results:List<User>)
data class User(
    val Country: String,
//    val Mfr_ID: String,
//    val Mfr_CommonName: String,
    val Mfr_Name: String,

)