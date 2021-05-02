package org.wit.carapp.activities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_car.view.*
import org.wit.carapp.R
import org.wit.carapp.models.CarappModel

interface CarListener {
    fun onCarClick(car: CarappModel)
}

class CarAdapter constructor(private var cars: List<CarappModel>,
                             private val listener: CarListener) :
        RecyclerView.Adapter<CarAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.card_car,
                        parent,
                        false
                )
        )
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val car = cars[holder.adapterPosition]
        holder.bind(car, listener)
    }

    override fun getItemCount(): Int = cars.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(car: CarappModel, listener: CarListener) {
            itemView.carMake.text = car.make
            itemView.carModel.text = car.model
            itemView.carYear.text = car.year
            itemView.setOnClickListener { listener.onCarClick(car)}
        }
    }
}
