package org.wit.carapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_carapp.*
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

        if (intent.hasExtra("car_edit")) {
            car = intent.extras?.getParcelable<CarappModel>("car_edit")!!
            addMake.setText(car.make)
            addModel.setText(car.model)
            addYear.setText(car.year)
        }

        btnAdd.setOnClickListener() {
            car.make = addMake.text.toString()
            car.model = addModel.text.toString()
            car.year = addYear.text.toString()
            if (car.make.isNotEmpty()) {
                app.cars.create(car.copy())
                info("add Button Pressed: ${car}")
                setResult(AppCompatActivity.RESULT_OK)
                finish()
            } else {
                toast("Please Enter a Make")
            }
        }
        //cancel toolbar



    }

    //cancel toolbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_car, menu)
        return super.onCreateOptionsMenu(menu)
    }

    //cancel toolbar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}