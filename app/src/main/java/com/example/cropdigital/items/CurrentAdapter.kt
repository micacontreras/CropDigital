package com.example.cropdigital.items

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cropdigital.R
import com.example.cropdigital.getHours
import com.example.cropdigital.network.ItemsResponse
import kotlinx.android.synthetic.main.row_item.view.*


class CustomAdapter(private val listener: OnItemClickListener? = null) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    private var dataSet = emptyList<ItemsResponse>()

    interface OnItemClickListener {
        fun onCloselicked(item: ItemsResponse)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(viewGroup.context).inflate(R.layout.row_item, viewGroup, false)
        )


    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = dataSet[position]
        viewHolder.bind(item)
    }

    override fun getItemCount() = dataSet.size

    fun addItems(items: List<ItemsResponse>) {
        this.dataSet = items
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(item: ItemsResponse) {
            itemView.idItem.text = item.index.toString()
            itemView.hour.text = getHours()
            itemView.parcela.text = item.parcel
            itemView.taskType.text = item.taskType
            itemView.coment.text = item.comment

            itemView.showMoreBtn.setOnClickListener { listener?.onCloselicked(item) }
        }
    }
}