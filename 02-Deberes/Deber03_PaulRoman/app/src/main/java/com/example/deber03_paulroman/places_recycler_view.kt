package com.example.deber03_paulroman

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class places_recycler_view(
    private val contexto: MainActivity,
    private var lista: List<Place>,
    private val recyclerView: RecyclerView
) : RecyclerView.Adapter<places_recycler_view.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val placeName: TextView = view.findViewById(R.id.tv_item_place_name)
        val placeCity: TextView = view.findViewById(R.id.tv_item_place_city)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.place_item_layout, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val curentPlace = lista[position]
        holder.placeName.text = curentPlace.name
        holder.placeCity.text = curentPlace.city
    }
    
}