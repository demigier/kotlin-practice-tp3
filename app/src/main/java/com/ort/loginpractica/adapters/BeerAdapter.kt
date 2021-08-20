package com.ort.loginpractica.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.ort.loginpractica.R
import com.ort.loginpractica.entities.Beer

class BeerAdapter (private var beerList: MutableList<Beer>, val onItemLongClick: (Int) -> Boolean) : RecyclerView.Adapter<BeerAdapter.BeerHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.item_beer,parent,false)
        return (BeerHolder(view))
    }

    override fun getItemCount(): Int {
        return beerList.size
    }

    override fun onBindViewHolder(holder: BeerHolder, position: Int) {
        holder.setName(beerList[position].name)

        holder.getCardLayout().setOnLongClickListener() {
            onItemLongClick(position)
        }
    }

    class BeerHolder (v: View) : RecyclerView.ViewHolder(v) {
        private var view: View = v

        fun setName(name: String) {
            val txt: TextView = view.findViewById(R.id.txtName)
            txt.text = name
        }
        fun getCardLayout (): CardView {
            return view.findViewById(R.id.cardBeer)
        }
    }
}