package com.example.proyecto_roman_campoverde

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto_roman_campoverde.Models.Grupo

class GrupoDetail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_group_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val grupo = intent.getParcelableExtra<Grupo>("materia")
        if (grupo != null) {
            // Use the Materia object, e.g., display details
            //findViewById<TextView>(R.id.tv_materia_detail_name).text = materia.nombre
            //findViewById<TextView>(R.id.tv_materia_detail_codigo).text = materia.codigo
        }
        val actionBar: ActionBar? = supportActionBar
        if (actionBar != null) {
            actionBar.setTitle(grupo?.nombre) // Establece el título
            actionBar.setDisplayHomeAsUpEnabled(true) // Muestra la flecha de regreso
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        finish() // Finaliza la actividad al hacer clic en la flecha de regreso
        return true
    }

}
