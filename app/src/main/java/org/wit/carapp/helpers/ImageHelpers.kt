package org.wit.carapp.helpers

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import org.wit.carapp.R
import java.io.IOException


import androidx.activity.result.ActivityResultLauncher


//fun showImagePicker(parent: Activity, id: Int) {
  //  val intent = Intent()
 //   intent.type = "image/*"
 //   intent.action = Intent.ACTION_OPEN_DOCUMENT
  //  intent.addCategory(Intent.CATEGORY_OPENABLE)
   // val chooser = Intent.createChooser(intent, R.string.select_car_image.toString())
   // parent.startActivityForResult(chooser, id)
//}

fun readImage(activity: Activity, resultCode: Int, data: Intent?): Bitmap? {
    var bitmap: Bitmap? = null
    if (resultCode == Activity.RESULT_OK && data != null && data.data != null) {
        try {
            bitmap = ImageDecoder.decodeBitmap(
                    ImageDecoder.createSource(activity.contentResolver, data.data!!
                    ))
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
    return bitmap
}

fun readImageFromPath(context: Context, path : String) : Bitmap? {
    var bitmap : Bitmap? = null
    val uri = Uri.parse(path)
    if (uri != null) {
        try {
            val parcelFileDescriptor = context.getContentResolver().openFileDescriptor(uri, "r")
            val fileDescriptor = parcelFileDescriptor?.getFileDescriptor()
            bitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor)
            parcelFileDescriptor?.close()
        } catch (e: Exception) {
        }
    }
    return bitmap
}









fun showImagePicker(intentLauncher : ActivityResultLauncher<Intent>) {
var chooseFile = Intent(Intent.ACTION_OPEN_DOCUMENT)
chooseFile.type = "image/*"
chooseFile = Intent.createChooser(chooseFile, R.string.select_car_image.toString())
intentLauncher.launch(chooseFile)
}