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
import com.example.proyecto_roman_campoverde.Models.Grupo

class FRecyclerViewAdaptadorGrupoMain(
    private val contexto: MainActivity,
    private var lista: List<Grupo>,
    private val recyclerView: RecyclerView
) : RecyclerView.Adapter<FRecyclerViewAdaptadorGrupoMain.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombreTextView: TextView = view.findViewById(R.id.tv_grupo_name)
        val cardView:CardView = view.findViewById(R.id.id_card_view_grupo_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.grupo_item_layout, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val grupoActual = lista[position]
            val materia: Materia? = contexto.dbHelper.obtenerMateriaPorId(grupoActual.idMateria)

            holder.nombreTextView.text = materia?.nombre +" - " + grupoActual.nombre
            holder.cardView.setOnClickListener {
                val intent = Intent(contexto, GrupoDetail::class.java)
                intent.putExtra("grupo", grupoActual)  // Pass the Materia object
                contexto.startActivity(intent)
            }
        }



    fun updateData(newLista: List<Grupo>) {
        lista = newLista
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(any: Any) {

    }
}

