package com.example.carfx.viewmodel

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carfx.model.CarList
import com.example.carfx.model.Listings
import com.example.carfx.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    private val Any.TAG: String get() = this::class.java.simpleName

    init {
        viewModelScope.launch(Dispatchers.IO) {
            mainRepository.getAllListing()
        }
    }

    val carList: LiveData<CarList>
        get() = mainRepository.carListLiveData


    fun callFromDailer(mContext: Context, number: String) {
        try {
            val callIntent = Intent(Intent.ACTION_DIAL)
            callIntent.data = Uri.parse("tel:$number")
            mContext.startActivity(callIntent)
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(mContext, "No SIM Found", Toast.LENGTH_LONG).show()
        }
    }

    fun printIndexData(indexObject: Listings) {

        indexObject.apply {

            Log.d(
                TAG,
                "---------------------------------------------------------------------------------"
            )
            Log.d(TAG, "Vehicle photo: ${images.firstPhoto.large}")
            Log.d(
                TAG,
                "Year Make Model Trim : $year  $make  $model  $trim"
            )
            Log.d(TAG, "Price: $currentPrice")
            Log.d(TAG, "Mileage: $mileage")
            Log.d(TAG, "Location: ${dealer.city} ,${dealer.state}")
            Log.d(TAG, "Phone: ${dealer.phone} ")
            Log.d(
                TAG,
                "---------------------------Vehicle INFO------------------------------------------------------"
            )
            Log.d(TAG, "Interior Color: $interiorColor")
            Log.d(TAG, "Exterior Color: $exteriorColor")
            Log.d(TAG, "Drive Type: $drivetype")
            Log.d(TAG, "Transmission: $transmission")
            Log.d(TAG, "Engine: $engine")
            Log.d(TAG, "Body Style: $bodytype")
        }

    }
}