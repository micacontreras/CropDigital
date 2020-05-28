package com.example.cropdigital.items

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentResultListener
import com.example.cropdigital.R
import com.example.cropdigital.getHours
import com.example.cropdigital.network.ItemsResponse
import kotlinx.android.synthetic.main.dialog.*
import kotlinx.android.synthetic.main.dialog.view.*


class ItemDialog : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog, container, false)
    }

    override fun onResume() {
        super.onResume()
        parentFragmentManager.setFragmentResultListener("ItemSelected", this,
            FragmentResultListener { _, bundle ->
                bundle.getParcelable<ItemsResponse>("Item").also {
                    createLoginDialog(it)
                }
            })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        close.setOnClickListener { dialog?.dismiss() }
    }

    private fun createLoginDialog(itemsResponse: ItemsResponse?): Dialog {
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
        modifyScreen(itemsResponse)
        return builder.create()
    }

    private fun modifyScreen(itemsResponse: ItemsResponse?) {
        idDialog.text = itemsResponse?.index.toString()
        hourDialog.text = getHours()
        parcelaDialog.text = itemsResponse?.parcel
        taskTypeDialog.text = itemsResponse?.taskType
        comentDialog.text = itemsResponse?.comment
    }
}
