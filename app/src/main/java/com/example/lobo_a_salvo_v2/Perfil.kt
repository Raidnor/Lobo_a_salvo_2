package com.example.lobo_a_salvo_v2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Perfil : AppCompatActivity() {
    private lateinit var correo_text:TextView
    private var correo = MainActivity.data.correo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        correo_text = findViewById<TextView>(R.id.correo_set)
        correo_text.setText(correo)
    }
}