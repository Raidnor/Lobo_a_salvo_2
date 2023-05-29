package com.example.lobo_a_salvo_v2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class Menu_mapas : AppCompatActivity() {
    val mapFragment = MapsActivity()
    lateinit var facultad: TextView
    lateinit var horario: TextView
    lateinit var yo:Button
    lateinit var distancia:TextView
    val items = listOf("Enfermeria FCC", "Enfermeria 2", "Enfermeria 3","Enfermeria 4","Enfermeria 5")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_mapas)
        //mi ubicacion
        yo = findViewById<Button>(R.id.mi_ubicacion)
        //obteniendo textview
        facultad = findViewById<TextView>(R.id.id_facultad)
        horario = findViewById<TextView>(R.id.id_horario)
        //cargando lista personalizada
        val adapter = CustomSpinnerAdapter(this,items)
        //
        distancia= findViewById<TextView>(R.id.distancia)
        //cargando elementos de la lista
        //val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        //
        val spinner = findViewById<Spinner>(R.id.list_servicios)
        spinner.adapter = adapter
        //desplegando lista
        supportFragmentManager.beginTransaction()
            .add(R.id.container_map, mapFragment)
            .commit()

        //mapFragment.newCoords(39.005008, -97.205075,"Nueva")
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                // Acciones a realizar cuando se selecciona un elemento del Spinner
                val selectedItem = parent.getItemAtPosition(position).toString()
                // condiciones de la lista
                when (selectedItem){
                    items[0]->{
                        mapFragment.newCoords(19.005008, -98.205075,selectedItem)
                        facultad.setText(selectedItem)
                        horario.setText("11:00 am - 05:00 pm")
                    }
                    items[1]->{
                        mapFragment.newCoords(49.005008, -87.205075,selectedItem)
                        facultad.setText(selectedItem)
                        horario.setText("11:00 am - 05:00 pm")
                    }
                    items[2]->{
                        mapFragment.newCoords(59.005008, -77.205075,selectedItem)
                        facultad.setText(selectedItem)
                        horario.setText("11:00 am - 05:00 pm")
                    }
                    items[3]->{
                        mapFragment.newCoords(69.005008, -87.205075,selectedItem)
                        facultad.setText(selectedItem)
                        horario.setText("11:00 am - 05:00 pm")
                    }
                    items[4]->{
                        mapFragment.newCoords(79.005008, -77.205075,selectedItem)
                        facultad.setText(selectedItem)
                        horario.setText("11:00 am - 05:00 pm")
                    }
                    items[5]->{
                        mapFragment.newCoords(88.005008, -77.205075,selectedItem)
                        facultad.setText(selectedItem)
                        horario.setText("11:00 am - 05:00 pm")
                    }
                }
                //boton de mi ubicacion
                yo.setOnClickListener {
                   // mapFragment.getLastKnownLocation()
                    mapFragment.ruta()
                    val resultado: String = "%.2f".format(mapFragment.distancia())
                    //println(resultado) // Imprimirá: 3.14

                    distancia.setText("Distancia: "+resultado+" km")
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Acciones a realizar cuando no se selecciona ningún elemento
            }
        }

    }



}