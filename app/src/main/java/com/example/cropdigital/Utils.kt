package com.example.cropdigital

import android.content.Context
import androidx.appcompat.app.AlertDialog
import java.util.*


fun showDialog(context: Context, title: String, message: String, positiveButton: String, positiveAction: (() -> Unit)? = null) {
    AlertDialog.Builder(context).apply {
        setTitle(title)
        setMessage(message)
        setPositiveButton(positiveButton) { _, _ -> positiveAction?.invoke() }
        create()
        show()
    }
}

fun getHours(): String {
    val hours = Calendar.getInstance().get(Calendar.HOUR)
    val minutes: Int = Calendar.getInstance().get(Calendar.MINUTE)
    return "$hours:$minutes"
}
