package com.example.cropdigital

import android.content.Context
import androidx.appcompat.app.AlertDialog
import java.util.*


fun showDialog(context: Context, title: String, message: String, positiveButton: String) {
    AlertDialog.Builder(context).apply {
        setTitle(title)
        setMessage(message)
        setPositiveButton(positiveButton) { _, _ -> }
        create()
        show()
    }
}

fun getHours(): String {
    val hours = Calendar.HOUR_OF_DAY
    val minutes: Int = Calendar.MINUTE
    return "$hours:$minutes"
}
