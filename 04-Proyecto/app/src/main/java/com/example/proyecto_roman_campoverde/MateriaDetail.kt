package com.example.proyecto_roman_campoverde

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.deber02_paulroman.Models.Materia

class MateriaDetail : AppCompatActivity() {
    private lateinit var recyclerView:RecyclerView
    private lateinit var  dbHelper: ESqliteHelper
    private lateinit var materia: Materia
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        dbHelper = ESqliteHelper.getInstance(this)

        materia = intent.getParcelableExtra<Materia>("materia")!!
        if (materia != null) {
            // Use the Materia object, e.g., display details
            //findViewById<TextView>(R.id.tv_materia_detail_name).text = materia.nombre
            //findViewById<TextView>(R.id.tv_materia_detail_codigo).text = materia.codigo
        }
        setContentView(R.layout.activity_materia_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val actionBar: ActionBar? = supportActionBar
        if (actionBar != null) {
            actionBar.setTitle(materia?.nombre) // Establece el título
            actionBar.setDisplayHomeAsUpEnabled(true) // Muestra la flecha de regreso
        }
        inicializarRecyclerView()

    }

    override fun onSupportNavigateUp(): Boolean {
        finish() // Finaliza la actividad al hacer clic en la flecha de regreso
        return true
    }

    fun inicializarRecyclerView() {
        recyclerView = findViewById<RecyclerView>(R.id.rv_grupos_materia)
        val adaptador = materia?.id?.let { dbHelper.obtenerGruposPorIdMateria(it) }?.let {
            FRecyclerViewAdaptadorGrupo(
                this,
                it,
                recyclerView
            )
        }
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        if (adaptador != null) {
            adaptador.notifyDataSetChanged()
        }
    }

}