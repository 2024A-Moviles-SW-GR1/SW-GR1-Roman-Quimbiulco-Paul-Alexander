package com.example.proyecto_roman_campoverde

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.deber02_paulroman.Models.Materia

class FRecyclerViewAdaptadorMateria(
    private val contexto: MainActivity,
    private var lista: List<Materia>,
    private val recyclerView: RecyclerView
) : RecyclerView.Adapter<FRecyclerViewAdaptadorMateria.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombreTextView: TextView = view.findViewById(R.id.tv_materia_name)
        val codigoTextView: TextView = view.findViewById(R.id.tv_materia_codigo)
        val cardView:CardView = view.findViewById(R.id.id_card_view_materia_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.materia_item_layout, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val materiaActual = lista[position]
            holder.nombreTextView.text = materiaActual.nombre
            holder.codigoTextView.text = materiaActual.codigo

            holder.cardView.setOnClickListener {
                val intent = Intent(contexto, MateriaDetail::class.java)
                intent.putExtra("materia", materiaActual)  // Pass the Materia object
                contexto.startActivity(intent)
            }
        }



    fun updateData(newLista: MutableList<Materia>) {
        lista = newLista
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(any: Any) {

    }
}

