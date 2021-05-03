package org.wit.carapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CarModel(var id: Long = 0,
                          var make: String = "",
                          var model: String = "",
                          var image: String = "") : Parcelable