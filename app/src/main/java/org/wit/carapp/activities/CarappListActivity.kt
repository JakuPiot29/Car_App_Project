package org.wit.carapp.activities

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_car_list.*
import kotlinx.android.synthetic.main.activity_carapp.*
import kotlinx.android.synthetic.main.card_car.view.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivityForResult
import org.wit.carapp.R
import org.wit.carapp.main.MainApp
import org.wit.carapp.models.CarappModel

class CarappListActivity : AppCompatActivity(), CarListener {

    lateinit var app: MainApp


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_list)
        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
       // recyclerView.adapter = CarAdapter(app.cars)
        recyclerView.adapter = CarAdapter(app.cars.findAll(), this)

        //enable action toolbar
        toolbar.title = title
        setSupportActionBar(toolbar)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> startActivityForResult<CarappActivity>(0)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCarClick(car: CarappModel) {
        startActivityForResult(intentFor<CarappActivity>().putExtra("car_edit", car), 0)
    }
}

