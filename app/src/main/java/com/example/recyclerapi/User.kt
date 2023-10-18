package com.example.recyclerapi

import android.os.Parcelable
import java.io.Serializable


data class Cars(val Results:List<User>):Serializable

data class User(
    val Country: String,
    val Mfr_ID: String,
    val Mfr_CommonName: String,
    val Mfr_Name: String,

):Serializable