package org.wit.carapp.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.carapp.models.CarJSONStore
import org.wit.carapp.models.CarMemStore
import org.wit.carapp.models.CarStore

class MainApp : Application(), AnkoLogger {


    lateinit var cars: CarStore

    override fun onCreate() {
        super.onCreate()
        cars = CarJSONStore(applicationContext)
        info("Car started")

    }
}