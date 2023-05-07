package com.example.helasaviya.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.helasaviya.R
import com.example.helasaviya.models.CreaditsModel

class CreditAdapter (private val creditList:ArrayList<CreaditsModel>):
    RecyclerView.Adapter<CreditAdapter.ViewHolder>(){

    private  lateinit var mListener: onItemClickListener
    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){
        mListener =clickListener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.credit_list_item,parent,false)
        return ViewHolder(itemView,mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val currentCredit= creditList[position]
        holder.tvCreditName.text = currentCredit.empcardnumber
    }



    override fun getItemCount(): Int {
       return creditList.size
    }


    class ViewHolder(itemView: View,clickListener: onItemClickListener): RecyclerView.ViewHolder(itemView) {
        val tvCreditName: TextView = itemView.findViewById(R.id.tvCreditName)

        init {
            itemView.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }


    }
    }