package com.example.lobo_a_salvo_v2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonNext: Button = findViewById(R.id.btn_iniciarSesion)
        buttonNext.setOnClickListener {
            val intent = Intent(this, Login_activity::class.java)
            startActivity(intent)
        }
    }
}