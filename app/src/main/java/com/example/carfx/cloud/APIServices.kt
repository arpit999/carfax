package com.example.carfx.cloud

import com.example.carfx.model.CarList
import retrofit2.Response
import retrofit2.http.GET

interface APIServices {

    @GET("/assignment.json")
    suspend fun getCarList():Response<CarList>

}