package org.wit.carapp.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_car.*
import kotlinx.android.synthetic.main.activity_car.carMake
import kotlinx.android.synthetic.main.card_car.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.carapp.R
import org.wit.carapp.databinding.ActivityCarBinding
import org.wit.carapp.helpers.showImagePicker
import org.wit.carapp.main.MainApp
import org.wit.carapp.models.CarModel
import timber.log.Timber.i

class CarActivity : AppCompatActivity(), AnkoLogger {
    private lateinit var imageIntentLauncher : ActivityResultLauncher<Intent>
    private lateinit var binding: ActivityCarBinding
    var car = CarModel()
    lateinit var app : MainApp
    var edit = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        registerImagePickerCallback()
        binding = ActivityCarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarAdd.title = title
        setSupportActionBar(binding.toolbarAdd)

        app = application as MainApp

        carYear.minValue = 1950
        carYear.maxValue = 2022
        carYear.wrapSelectorWheel = false
        carYear.value = 2022










        if (intent.hasExtra("car_edit")) {
            edit = true
            car = intent.extras?.getParcelable<CarModel>("car_edit")!!
            carMake.setText(car.make)
            carModel.setText(car.model)
            carYear.value = car.year
            carEngine.setText(car.engine.toString())
            Picasso.get()
                .load(car.image)
                .into(binding.carImage)
            if (car.image != Uri.EMPTY) {
                binding.chooseImage.setText(R.string.change_car_image)
            }
            btnAdd.setText(R.string.save_car)
        }

        btnAdd.setOnClickListener() {


            car.make = carMake.text.toString()
            car.model = carModel.text.toString()
            car.year = carYear.value
            car.engine = carEngine.text.toString().toDouble()


            if (car.make.trim().length == 0) {
                toast(R.string.enter_car_make)

            }
            else if (car.model.trim().length == 0) {
                toast(R.string.enter_car_model)

            }
            else if (car.model.trim().length == 0) {
                toast(R.string.enter_car_model)

            }
            else if (car.engine <= 0 || car.engine.toString() == null) {
                toast(R.string.enter_car_engine)

            }
            else if (car.image == Uri.EMPTY) {
                toast(R.string.enter_car_image)

            }







            else {
                if (edit) {
                    app.cars.update(car.copy())
                    info("add Button Pressed: $carMake")
                    setResult(RESULT_OK)
                    finish()
                    toast(R.string.car_updated)


                } else {
                    app.cars.create(car.copy())
                    info("add Button Pressed: $carMake")
                    setResult(RESULT_OK)
                    finish()
                    toast(R.string.car_added)

                }

            }



        }

       /** toolbarAdd.title = title
        setSupportActionBar(toolbarAdd)
       **/


        binding.chooseImage.setOnClickListener {
            showImagePicker(imageIntentLauncher)
            toast(R.string.enter_car_image)
        }

      /**  chooseImage.setOnClickListener {
            showImagePicker(this, IMAGE_REQUEST)
        }**/




    }




    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_car, menu)
        if (edit) menu.getItem(0).isVisible = true
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_delete -> {
                app.cars.delete(car)
                finish()
                toast(R.string.car_deleted)
            }
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
/**
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            IMAGE_REQUEST -> {
                if (data != null) {
                    car.image = data.getData().toString()
                    carImage.setImageBitmap(readImage(this, resultCode, data))
                    chooseImage.setText(R.string.change_car_image)
                }
            }
        }
    }

    **/


private fun registerImagePickerCallback() {
    imageIntentLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        { result ->
            when(result.resultCode){
                RESULT_OK -> {
                    if (result.data != null) {
                        i("Got Result ${result.data!!.data}")
                        car.image = result.data!!.data!!
                        Picasso.get()
                            .load(car.image)
                            .into(binding.carImage)
                        binding.chooseImage.setText(R.string.change_car_image)
                    } // end of if
                }
                RESULT_CANCELED -> { } else -> { }
            }
        }
}


}