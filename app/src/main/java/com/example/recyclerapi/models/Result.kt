package com.example.recyclerapi.models

import java.io.Serializable

data class Result(
    val Country: String,
    val Mfr_CommonName: String,
    val Mfr_ID: Int,
    val Mfr_Name: String,

): Serializable