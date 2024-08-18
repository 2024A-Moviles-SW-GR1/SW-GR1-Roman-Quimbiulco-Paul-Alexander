package com.example.proyecto_roman_campoverde

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

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
        // Configurar RecyclerView con un layout manager y adaptador
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = FRecyclerViewAdaptadorMateria(this,dbHelper.obtenerMaterias(),recyclerView) // Inicializa el adaptador con tus datos
        recyclerView.adapter = adapter

        // Mostrar el RecyclerView cuando el EditText recibe foco
        editTextSearch.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                recyclerView.visibility = View.VISIBLE
            } else {
                recyclerView.visibility = View.GONE
            }
        }

        // Opcional: Ocultar el RecyclerView cuando se selecciona una opción
        adapter.setOnItemClickListener { selectedItem:Int ->
            editTextSearch.setText(selectedItem)
            recyclerView.visibility = View.GONE
        }
    }

    private fun getSearchOptions(): List<String> {
        return listOf("Materia 1", "Materia 2", "Materia 3", "Materia 4", "Materia 5")
    }

    fun irActividad(
        clase: Class<*>
    ){
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}