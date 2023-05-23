package com.example.lobo_a_salvo_v2

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageButton
import android.widget.TextView


class Llamando : AppCompatActivity() {
    private lateinit var btn_colgar:ImageButton
    private val tiempoEspera = 30000L // 30 segundos
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable
    private lateinit var mediaPlayer:MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_llamando)
        recibiendo()
        reproducirSonido()
        btn_colgar= findViewById<ImageButton>(R.id.btn_colgar)

        //funciones
        colgar()
// Inicializar el Handler
        handler = Handler()

        // Programar la finalización de la actividad después de 30 segundos
        runnable = Runnable {
            // Finalizar la actividad
            terminar()
        }
        handler.postDelayed(runnable, 30000) // 30 segundos en milisegundos
    }

    override fun onDestroy() {
        super.onDestroy()
        // Detener el Runnable si la actividad se destruye antes de los 30 segundos
        handler.removeCallbacks(runnable)
    }
    fun recibiendo(){
        var estado:Boolean = Principal_activity.Llamada_status.estatus
        var cadena:String = Principal_activity.Llamada_status.tipo
        cambiarTexto(cadena)
    }

    fun colgar(){
        btn_colgar.setOnClickListener{
            terminar()
        }
    }
    fun terminar(){
        Principal_activity.Llamada_status.estatus = false
        Principal_activity.Llamada_status.tipo = ""
        mediaPlayer.stop()
        finish()
    }
    fun cambiarTexto(cadena:String) {
        // Obtén una referencia al TextView
        val textView = findViewById<TextView>(R.id.tipo_servicio)

        // Cambia el texto del TextView
        textView.text = cadena
    }
    fun reproducirSonido() {
      mediaPlayer = MediaPlayer.create(this, R.raw.tono)
        mediaPlayer.isLooping = true
        mediaPlayer.start()

    }

}