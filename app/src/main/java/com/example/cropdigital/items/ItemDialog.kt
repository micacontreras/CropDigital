package com.example.cropdigital.items

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentResultListener
import com.example.cropdigital.R
import com.example.cropdigital.getHours
import com.example.cropdigital.network.ItemsResponse
import kotlinx.android.synthetic.main.dialog.*
import kotlinx.android.synthetic.main.dialog.view.*


class ItemDialog : DialogFragment() {

    override fun onResume() {
        super.onResume()
        parentFragmentManager.setFragmentResultListener("ItemSelected", this,
            FragmentResultListener { _, bundle ->
                bundle.getParcelable<ItemsResponse>("Item").also {
                    createLoginDialog(it)
                }
            })
    }

    private fun createLoginDialog(itemsResponse: ItemsResponse?): Dialog {
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
        val v =
            requireActivity().layoutInflater.inflate(R.layout.dialog, null)
        modifyScreen(v, itemsResponse)
        builder.setView(v)
        close?.setOnClickListener {
            DialogInterface.OnClickListener { dialog, _ ->
                dialog.dismiss()
            }
        }

        return builder.create()
    }

    private fun modifyScreen(view: View, itemsResponse: ItemsResponse?) {
        view.idDialog.text = itemsResponse?.index.toString()
        view.hourDialog.text = getHours()
        view.parcelaDialog.text = itemsResponse?.parcel
        view.taskTypeDialog.text = itemsResponse?.taskType
        view.comentDialog.text = itemsResponse?.comment
    }

}
