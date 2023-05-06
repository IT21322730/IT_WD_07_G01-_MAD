package com.example.helasaviya_delivery.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.helasaviya_delivery.R
import com.example.helasaviya_delivery.models.DeliveryModel

class deliveryAdaptor (private val deliveryList: java.util.ArrayList<DeliveryModel>):
    RecyclerView.Adapter<deliveryAdaptor.ViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener:onItemClickListener){
        mListener = clickListener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.emp_list_item, parent, false)
        return ViewHolder(itemView,mListener)
    }

    override fun onBindViewHolder(holder: deliveryAdaptor.ViewHolder, position: Int) {
        val currentdelivery =deliveryList[position]
        holder.tvdeliveryName.text = currentdelivery.name
        holder.tvDeliveryStatus.text = currentdelivery.status
        holder.tvDeliveryid.text = currentdelivery.id
    }


    override fun getItemCount(): Int {

        return deliveryList.size
    }

    class ViewHolder(itemView:View,clickListener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val tvdeliveryName : TextView = itemView.findViewById(R.id.tvDeliveryName)
        val tvDeliveryStatus : TextView = itemView.findViewById(R.id.tvDeliveryStatus)
        val tvDeliveryid : TextView = itemView.findViewById(R.id.tvDeliveryid)

        init{
            itemView.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }

    }



}