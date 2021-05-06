package org.wit.carapp.models

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.AnkoLogger
import org.wit.carapp.helpers.*
import java.util.*

val JSON_FILE = "cars.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<CarModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class CarJSONStore : CarStore, AnkoLogger {

    val context: Context
    var cars = mutableListOf<CarModel>()

    constructor (context: Context) {
        this.context = context
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<CarModel> {
        return cars
    }

    override fun create(car: CarModel) {
        car.id = generateRandomId()
        cars.add(car)
        serialize()
    }

    override fun update(car: CarModel) {
        val placemarksList = findAll() as ArrayList<CarModel>
        var foundCar: CarModel? = placemarksList.find { p -> p.id == car.id }
        if (foundCar != null) {
            foundCar.make = car.make
            foundCar.model = car.model
            foundCar.year = car.year
            foundCar.image = car.image

        }
        serialize()
    }

    override fun delete(car: CarModel) {
        cars.remove(car)
        serialize()
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(cars, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        cars = Gson().fromJson(jsonString, listType)
    }
}