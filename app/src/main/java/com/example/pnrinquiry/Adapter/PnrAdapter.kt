package com.example.pnrinquiry.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pnrinquiry.Model.BaseResponse
import com.example.pnrinquiry.Model.Passenger
import com.example.pnrinquiry.R

class PnrAdapter(val context: Context, val passenger: ArrayList<Passenger>) :
    RecyclerView.Adapter<PnrAdapter.ViewHolder>() {
    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(itemModel : Passenger)
        {
            val psgn_name : TextView = itemView.findViewById(R.id.psgn_name)
            val bkg_status : TextView = itemView.findViewById(R.id.bkg_status)
            val crnt_status_label : TextView = itemView.findViewById(R.id.crnt_status_label)

            psgn_name.setText(itemModel.name)
            bkg_status.setText(itemModel.booking_status)
            crnt_status_label.setText(itemModel.current_status)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pnr_passanger,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return passenger.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(passenger[position])
    }
}