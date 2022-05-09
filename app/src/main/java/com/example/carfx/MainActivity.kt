package com.example.carfx

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.carfx.adapter.RecyclerAdapter
import com.example.carfx.cloud.APIServices
import com.example.carfx.cloud.RetrofitHelper
import com.example.carfx.repository.MainRepository
import com.example.carfx.utils.RecyclerItemClickListener
import com.example.carfx.viewmodel.MainViewModel
import com.example.carfx.viewmodel.MainViewModelFactory


class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel

    private val Any.TAG: String get() = this::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val recyclerView: RecyclerView = findViewById(R.id.reyclerView)

        val apiServices = RetrofitHelper.getInstance().create(APIServices::class.java)
        val mainRepository = MainRepository(apiServices)
        mainViewModel = ViewModelProvider(
            this,
            MainViewModelFactory(mainRepository)
        )[MainViewModel::class.java]

        mainViewModel.carList.observe(this, Observer {

            recyclerView.adapter = RecyclerAdapter(it, applicationContext)
            recyclerView.layoutManager = LinearLayoutManager(applicationContext)
            recyclerView.setHasFixedSize(true)

            mainViewModel.printIndexData(it.listings[1])

        })


    }


}