package com.example.deber03_paulroman

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        inicializarRecyclerViewSuggestions()
        inicializarRecyclerViewPlaces()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun inicializarRecyclerViewSuggestions() {
        val listSuggestions: List<Suggestion> = listOf(
            Suggestion("Viaje",R.drawable.carro),
            Suggestion("Envíos", R.drawable.paquete),
            Suggestion("Encargo", R.drawable.encargo),
            Suggestion("Reserve", R.drawable.calendario),
            Suggestion("Viaje XL",R.drawable.uber_personas)
        )

        val recyclerViewSuggestions: RecyclerView = findViewById<RecyclerView>(R.id.rv_suggestions)
        val adaptador = suggestions_recycler_view(
            this,
            listSuggestions,
            recyclerViewSuggestions
        )

        recyclerViewSuggestions.adapter = adaptador
        recyclerViewSuggestions.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerViewSuggestions.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    fun inicializarRecyclerViewPlaces() {
        val listPlaces: List<Place> = listOf(
            Place("Aeropuerto 2", "Quito"),
            Place("Rafael Ramos y Avenida 10 de Agosto", "Quito"),
            Place("Plaza Foch", "Quito"),
            Place("Parque La Carolina", "Quito")
        )

        val recyclerViewPlaces: RecyclerView = findViewById<RecyclerView>(R.id.rv_last_places)
        val adaptador = places_recycler_view(
            this,
            listPlaces,
            recyclerViewPlaces
        )

        recyclerViewPlaces.adapter = adaptador
        recyclerViewPlaces.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerViewPlaces.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }
}