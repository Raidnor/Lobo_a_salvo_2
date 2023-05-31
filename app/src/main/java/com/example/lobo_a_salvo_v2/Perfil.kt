package com.example.lobo_a_salvo_v2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class Perfil : AppCompatActivity() {
    private lateinit var foto_perfil:ImageView
    private lateinit var correo_text:TextView
    private var correo = MainActivity.data.correo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        correo_text = findViewById<TextView>(R.id.correo_set)
        correo_text.setText(correo)
        foto_perfil = findViewById<ImageView>(R.id.imageView5)
        Glide.with(this)
            .load(R.drawable.bremerton_ship)
            .circleCrop()
            .into(foto_perfil)
    }
}