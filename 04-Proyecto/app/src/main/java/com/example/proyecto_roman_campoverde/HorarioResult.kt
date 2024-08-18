package com.example.proyecto_roman_campoverde

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HorarioResult : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_horario_result)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val actionBar: ActionBar? = supportActionBar
        if (actionBar != null) {
            actionBar.setTitle("Tu Título") // Establece el título
            actionBar.setDisplayHomeAsUpEnabled(true) // Muestra la flecha de regreso
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish() // Finaliza la actividad al hacer clic en la flecha de regreso
        return true
    }
}