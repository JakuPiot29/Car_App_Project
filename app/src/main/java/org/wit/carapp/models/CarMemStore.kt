package org.wit.carapp.models

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class CarMemStore : CarStore, AnkoLogger {

    val cars = ArrayList<CarModel>()

    override fun findAll(): List<CarModel> {
        return cars
    }

    override fun create(car: CarModel) {
        car.id = getId()
        cars.add(car)
        logAll()
    }

    override fun update(car: CarModel) {
        var foundCar: CarModel? = cars.find { p -> p.id == car.id }
        if (foundCar != null) {
            foundCar.make = car.make
            foundCar.model = car.model
            foundCar.image = car.image
            logAll()
        }
    }

    fun logAll() {
        cars.forEach { info("${it}") }
    }

    override fun delete(car: CarModel) {
        cars.remove(car)
    }
}