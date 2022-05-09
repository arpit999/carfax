package com.example.carfx

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.carfx.databinding.DetailViewBinding
import com.example.carfx.model.CarList
import com.example.carfx.model.Listings
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {

    lateinit var binding: DetailViewBinding

    private val Any.TAG: String get() = this::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.detail_view)

        val listings = intent.getSerializableExtra("LISTING") as? Listings

        binding.listing = listings


    }


}