package com.example.carfx.model

import java.io.Serializable
import java.text.NumberFormat
import java.util.*

data class Listings(
    val bodytype: String,
    var currentPrice: Int,
    val dealer: Dealer,
    val drivetype: String,
    val engine: String,
    val exteriorColor: String,
    val images: Images,
    val interiorColor: String,
    val listPrice: Int,
    val make: String,
    val mileage: Double,
    val model: String,
    val trim: String,
    val transmission: String,
    val vehicleCondition: String,
    val vin: String,
    val year: Int
): Serializable

