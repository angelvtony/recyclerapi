package com.example.recyclerapi

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable


data class Cars(val Results:List<User>):Serializable
@Parcelize
data class User(
    val Country: String,
    val Mfr_ID: String,
    val Mfr_CommonName: String,
    val Mfr_Name: String,
    val VehicleTypes: List<VehicleType>

):Parcelable
@Parcelize
data class VehicleType(
    val IsPrimary: Boolean,
    val Name: String
):Parcelable
