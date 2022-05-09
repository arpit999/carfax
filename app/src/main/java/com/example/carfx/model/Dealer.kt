package com.example.carfx.model

import java.io.Serializable

data class Dealer(
    val city: String,
    val name: String,
    val phone: String,
    val state: String,
    val zip: String
): Serializable