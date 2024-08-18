package com.example.proyecto_roman_campoverde

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto_roman_campoverde.Models.GrupoManager
class MainActivity : AppCompatActivity() {
    private lateinit var editTextSearch: EditText
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FRecyclerViewAdaptadorMateria // Define tu adaptador personalizado
    public lateinit var dbHelper: ESqliteHelper;
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val editTextSearch: EditText = findViewById(R.id.et_materia_search_main)
        ESqliteHelper(this)
        dbHelper = ESqliteHelper.getInstance(this)
        recyclerView = findViewById<RecyclerView>(R.id.rv_options)
        val btnVolver =findViewById<Button>(R.id.btn_volver)

        // Configurar RecyclerView con un layout manager y adaptador
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = FRecyclerViewAdaptadorMateria(this,dbHelper.obtenerMaterias(),recyclerView) // Inicializa el adaptador con tus datos
        recyclerView.adapter = adapter

        // Mostrar el RecyclerView cuando el EditText recibe foco
        editTextSearch.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                recyclerView.visibility = View.VISIBLE
                btnVolver.visibility = View.VISIBLE
            }
        }
        inicializarRecyclerView()

        btnVolver.setOnClickListener{btn ->
            btnVolver.visibility = View.GONE
            recyclerView.visibility = View.GONE
            editTextSearch.clearFocus()
        }

        // Opcional: Ocultar el RecyclerView cuando se selecciona una opción
        adapter.setOnItemClickListener { selectedItem:Int ->
            editTextSearch.setText(selectedItem)
            recyclerView.visibility = View.GONE
        }

        val btnGenerarHorario = findViewById<Button>(R.id.btn_generar_horario)
        btnGenerarHorario.setOnClickListener{btn ->
            irActividad(HorarioResult::class.java)
        }
    }

    fun inicializarRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.rv_main_final_groups)
        val grupos = GrupoManager.obtenerGrupos()
        val adaptador = FRecyclerViewAdaptadorGrupoMain(
            this,
            grupos,
            recyclerView
        )
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        if (adaptador != null) {
            adaptador.notifyDataSetChanged()
        }
    }

    fun irActividad(
        clase: Class<*>
    ){
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}