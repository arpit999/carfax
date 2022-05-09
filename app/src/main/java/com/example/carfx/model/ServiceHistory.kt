package com.example.carfx.model

import java.io.Serializable

data class ServiceHistory(
    val icon: String,
    val iconUrl: String,
    val number: Int,
    val text: String
): Serializable