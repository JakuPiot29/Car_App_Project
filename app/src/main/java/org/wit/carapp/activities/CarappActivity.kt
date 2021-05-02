package org.wit.carapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_carapp.*
import kotlinx.android.synthetic.main.activity_carapp.addMake
import kotlinx.android.synthetic.main.card_car.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.carapp.R
import org.wit.carapp.main.MainApp
import org.wit.carapp.models.CarappModel

class CarappActivity : AppCompatActivity(), AnkoLogger {

    var car = CarappModel()
    lateinit var app : MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carapp)
        app = application as MainApp

        btnAdd.setOnClickListener() {
            car.make = addMake.text.toString()
            car.model = addModel.text.toString()
            car.year = addYear.text.toString()
            if (car.make.isNotEmpty()) {
                app.cars.add(car.copy())
                info("add Button Pressed: ${car}")
                for (i in app.cars.indices) {
                    info("Car[$i]:${app.cars[i]}")
                }
                setResult(AppCompatActivity.RESULT_OK)
                finish()
            } else {
                toast("Please Enter a Make")
            }
        }
    }
}