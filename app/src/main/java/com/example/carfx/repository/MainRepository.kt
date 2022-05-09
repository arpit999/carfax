package com.example.carfx.repository

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.carfx.cloud.APIServices
import com.example.carfx.model.CarList

class MainRepository(private val apiServices: APIServices) {

    private var carList: MutableLiveData<CarList> = MutableLiveData()

    val carListLiveData: LiveData<CarList>
        get() = carList

    suspend fun getAllListing() {
        val result = apiServices.getCarList()

        result.body()?.let {
            carList.postValue(result.body())
        }
    }


}