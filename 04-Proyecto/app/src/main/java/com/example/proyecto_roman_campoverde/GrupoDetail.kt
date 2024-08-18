package com.example.proyecto_roman_campoverde

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto_roman_campoverde.Models.Grupo
import com.example.proyecto_roman_campoverde.Models.GrupoManager

class GrupoDetail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_group_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.group_activity_layout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val grupo = intent.getParcelableExtra<Grupo>("grupo")
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

// Referencias para Lunes
        val horaInicioLunes = findViewById<TextView>(R.id.tv_hora_desde_lunes)
        val horaHastaLunes = findViewById<TextView>(R.id.tv_hora_hasta_lunes)
        horaInicioLunes.text = grupo?.horaInicioLunes.toString() + ":00"
        horaHastaLunes.text = grupo?.horaFinLunes.toString() + ":00"

// Referencias para Martes
        val horaInicioMartes = findViewById<TextView>(R.id.tv_hora_desde_martes)
        val horaHastaMartes = findViewById<TextView>(R.id.tv_hora_hasta_martes)
        horaInicioMartes.text = grupo?.horaInicioMartes.toString() + ":00"
        horaHastaMartes.text = grupo?.horaFinMartes.toString() + ":00"

// Referencias para Miércoles
        val horaInicioMiercoles = findViewById<TextView>(R.id.tv_hora_desde_miercoles)
        val horaHastaMiercoles = findViewById<TextView>(R.id.tv_hora_hasta_miercoles)
        horaInicioMiercoles.text = grupo?.horaInicioMiercoles.toString() + ":00"
        horaHastaMiercoles.text = grupo?.horaFinMiercoles.toString() + ":00"

// Referencias para Jueves
        val horaInicioJueves = findViewById<TextView>(R.id.tv_hora_desde_jueves)
        val horaHastaJueves = findViewById<TextView>(R.id.tv_hora_hasta_jueves)
        horaInicioJueves.text = grupo?.horaInicioJueves.toString() + ":00"
        horaHastaJueves.text = grupo?.horaFinJueves.toString() + ":00"

// Referencias para Viernes
        val horaInicioViernes = findViewById<TextView>(R.id.tv_hora_desde_viernes)
        val horaHastaViernes = findViewById<TextView>(R.id.tv_hora_hasta_viernes)
        horaInicioViernes.text = grupo?.horaInicioViernes.toString() + ":00"
        horaHastaViernes.text = grupo?.horaFinViernes.toString() + ":00"


        val btnAgregar = findViewById<Button>(R.id.btn_agregarGrupo)
        btnAgregar.setOnClickListener { btn ->
            // Set the background color using a color resource
            btn.setBackgroundColor(ContextCompat.getColor(this, R.color.background))
            // Change the button text
            btnAgregar.text = "Quitar"
            // Call the method to add the group
            agregarGrupo(grupo)
        }


    }

    fun agregarGrupo(grupo: Grupo?){
        if (grupo != null) {
            GrupoManager.añadirGrupo(grupo)
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        finish() // Finaliza la actividad al hacer clic en la flecha de regreso
        return true
    }

}
