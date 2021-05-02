package org.wit.carapp.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.carapp.models.CarMemStore
import org.wit.carapp.models.CarappModel

class MainApp : Application(), AnkoLogger {

    //val cars = ArrayList<CarappModel>()
    val cars = CarMemStore()

    override fun onCreate() {
        super.onCreate()
        info("Car App started")
    }
}