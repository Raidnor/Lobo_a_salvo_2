package com.example.lobo_a_salvo_v2

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class Principal_activity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        val card: CardView = findViewById<CardView>(R.id.categoria_seguridad)
        val button: Button = card.findViewById<Button>(R.id.btn_seguridad)

        button.setOnClickListener{
            Toast.makeText(this,"Boton seguridad",Toast.LENGTH_SHORT).show()
        }


    }
}