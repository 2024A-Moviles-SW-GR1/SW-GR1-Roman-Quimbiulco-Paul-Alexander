package com.example.deber03_paulroman

import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class suggestions_recycler_view(
    private val contexto: MainActivity,
    private var lista: List<Suggestion>,
    private val recyclerView: RecyclerView
) : RecyclerView.Adapter<suggestions_recycler_view.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val suggestionImageView: ImageView = view.findViewById(R.id.iv_suggestion)
        val suggestionTextView: TextView = view.findViewById(R.id.tv_suggestion)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.uber_ways_use, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentSuggestion = lista[position]
        holder.suggestionTextView.text = currentSuggestion.title
        holder.suggestionImageView.setImageResource(currentSuggestion.imageResId)
    }

}