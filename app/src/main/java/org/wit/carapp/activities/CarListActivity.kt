package org.wit.carapp.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_car_list.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivityForResult
import org.wit.carapp.R
import org.wit.carapp.databinding.ActivityCarListBinding
import org.wit.carapp.main.MainApp
import org.wit.carapp.models.CarModel

class CarListActivity : AppCompatActivity(), CarListener {

    lateinit var app: MainApp
    private lateinit var refreshIntentLauncher : ActivityResultLauncher<Intent>
    private lateinit var binding: ActivityCarListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       /** setContentView(R.layout.activity_car_list)**/

        binding = ActivityCarListBinding.inflate(layoutInflater)
        setContentView(binding.root)



        app = application as MainApp


        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = CarAdapter(app.cars.findAll(), this )

        loadCars()
        registerRefreshCallback()

        toolbar.title = title
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> startActivityForResult<CarActivity>(0)
            R.id.item_logout -> { doLogout() }
            }
        return super.onOptionsItemSelected(item)
    }
    override fun onCarClick(car: CarModel) {
        startActivityForResult(intentFor<CarActivity>().putExtra("car_edit", car), 0)
    }

    @SuppressLint("NotifyDataSetChanged")
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        loadCars()
        //recyclerView is a widget in activity_car_list.xml
        recyclerView.adapter?.notifyDataSetChanged()
        super.onActivityResult(requestCode, resultCode, data)

    }

    private fun registerRefreshCallback() {
        refreshIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { loadCars() }
    }


    private fun loadCars() {
        showCars(app.cars.findAll())
    }

    @SuppressLint("NotifyDataSetChanged")
    fun showCars (cars: List<CarModel>) {
        binding.recyclerView.adapter = CarAdapter(cars, this)
        binding.recyclerView.adapter?.notifyDataSetChanged()
    }

    fun doLogout() {
        val intent = Intent(this, LoginView::class.java)
        startActivity(intent)
        finish()
    }



}

