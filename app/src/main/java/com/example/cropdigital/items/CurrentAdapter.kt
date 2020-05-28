package com.example.cropdigital.items

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cropdigital.R
import com.example.cropdigital.getHours
import com.example.cropdigital.network.ItemsResponse
import kotlinx.android.synthetic.main.row_item.view.*

class CustomAdapter :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    lateinit var onClick: (ItemsResponse) -> Unit
    private var dataSet = emptyList<ItemsResponse>()


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.row_item, viewGroup, false)
        return this.ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = dataSet[position]
        viewHolder.bindResponse(item, onClick)
    }

    override fun getItemCount() =  dataSet.size

    fun addItems(items: List<ItemsResponse>) {
        this.dataSet = items
        notifyDataSetChanged()
    }

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        private var view: View = v

        fun bindResponse(itemsResponse: ItemsResponse, onClick: (ItemsResponse) -> Unit) = with(itemView){
            view.idItem.text = itemsResponse.index.toString()
            view.hour.text = getHours()
            view.parcela.text = itemsResponse.parcel
            view.taskType.text = itemsResponse.taskType
            view.coment.text = itemsResponse.comment
            setOnClickListener{ onClick(itemsResponse) }
        }
    }
}