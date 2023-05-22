package com.example.lobo_a_salvo_v2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonLogin: Button = findViewById(R.id.btn_iniciarSesion)
        val buttonRegister: Button = findViewById(R.id.btn_registro)
        buttonLogin.setOnClickListener {
            val intent = Intent(this, Login_activity::class.java)
            startActivity(intent)
        }

        buttonRegister.setOnClickListener{
            val intent = Intent(this, Registrar_activity::class.java)
            startActivity(intent)
        }
    }
}