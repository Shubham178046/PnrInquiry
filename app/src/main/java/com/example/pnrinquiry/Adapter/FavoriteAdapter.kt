package com.example.pnrinquiry.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pnrinquiry.Model.FavoriteModel
import com.example.pnrinquiry.R
import kotlinx.android.synthetic.main.favorite_panlist.view.*

class FavoriteAdapter(context: Context, val favoriteModel: ArrayList<FavoriteModel>) :
    RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(itemModel: FavoriteModel) {
            val pnr_number : TextView = itemView.findViewById(R.id.tv_pnr_number)
            val name : TextView = itemView.findViewById(R.id.tv_fav_pnr)
            val doj : TextView = itemView.findViewById(R.id.tv_doj_pnr)

            pnr_number.setText(itemModel.pnr)
            name.setText(itemModel.name)
            doj.setText(itemModel.doj)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.favorite_panlist, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return favoriteModel.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(favoriteModel[position])

        holder.itemView.pnr_delete.setOnClickListener {
            favoriteModel.removeAt(holder.adapterPosition)
            notifyDataSetChanged()
        }
    }

}