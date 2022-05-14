package org.wit.carapp.main

import android.app.Application
import android.util.Log.i
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast

import org.wit.carapp.models.CarMemStore
import org.wit.carapp.models.CarStore
import org.wit.carapp.models.CarJSONStore
import timber.log.Timber

class MainApp : Application(), AnkoLogger {


    lateinit var cars: CarStore

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        cars = CarJSONStore(applicationContext)
        toast("Car App started")
    }
}