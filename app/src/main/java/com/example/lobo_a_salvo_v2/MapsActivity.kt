package com.example.lobo_a_salvo_v2

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.Path.Direction
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.lobo_a_salvo_v2.databinding.ActivityMapsBinding
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.BitmapDescriptorFactory

import com.google.android.gms.maps.model.PolylineOptions
import com.google.android.gms.maps.model.Polyline

import android.location.Location

class MapsActivity : Fragment(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private  var estado: Boolean = false
    private var location:Array<Double> = arrayOf(0.0,0.0)
    private var destino:Array<Double> = arrayOf(0.0,0.0)
    private var polylineOptions = PolylineOptions()
    private var trazado = null
    private var ruta = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?  {
        super.onCreate(savedInstanceState)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        binding = ActivityMapsBinding.inflate(inflater, container ,false)
        return binding.root

       /*// Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)*/
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap){
        mMap = googleMap
        getLastKnownLocation()
        // Add a marker in Sydney and move the camera
        //val sydney = LatLng(-34.0, 151.0)
        //mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        estado = true
    }

    //obtenemos nuevos marcadores.
     fun createMarker(latitud:Double,longitud:Double) {

        val ubicacion_actual = LatLng(latitud,longitud)
        location = arrayOf(latitud,longitud)
        mMap.addMarker(MarkerOptions().position(ubicacion_actual).title("Yo"));
        mMap.animateCamera(
            CameraUpdateFactory.newLatLngZoom(ubicacion_actual, 30f),
            5000,
            null
        )
    }

    fun newCoords(latitud:Double,longitud:Double,nombre:String){
        if(estado==true){
        val enfermeria = LatLng(latitud, longitud)
            destino = arrayOf(latitud,longitud)
            //dando color al marcador
            val markerOptions = MarkerOptions()
                .position(enfermeria)
                .title(nombre)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))

            mMap.addMarker(markerOptions)

            //mMap.addMarker(MarkerOptions().position(enfermeria).title(nombre))
            mMap.animateCamera(
            CameraUpdateFactory.newLatLngZoom(enfermeria, 18f),
            4000,
            null
        )
        }
    }

     fun getLastKnownLocation() {
        if (ActivityCompat.checkSelfPermission(
                requireActivity(),
                ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireActivity(),
                ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                if (location != null) {
                    // Aquí tienes la ubicación actual del dispositivo
                    val latitude = location.latitude
                    val longitude = location.longitude
                    createMarker(latitude,longitude)
                    // Utiliza las coordenadas como necesites
                }
            }
            .addOnFailureListener { exception ->
                // Maneja cualquier error que pueda ocurrir al obtener la ubicación
                exception.printStackTrace()
            }
    }



// Trazando ruta


    fun ruta(){

        if (ruta==false){
            val origen = LatLng(location[0], location[1])
            val destino = LatLng(this.destino[0], this.destino[1])

// Configura las opciones de la polilínea
            polylineOptions = PolylineOptions()
                .add(origen)
                .add(destino)
                .color(Color.RED)
                .width(5f)

// Añade la polilínea al mapa

            mMap.addPolyline(polylineOptions)
            ruta = true
        }else{
            mMap.addPolyline(polylineOptions).remove()
            ruta = false
            ruta()
        }

    }

    fun distancia():Float{

        // Crear objetos de ubicación para las coordenadas de origen y destino
                val origen = Location("Origen")
                origen.latitude = this.location[0]
                origen.longitude = this.location[1]

                val destino = Location("Destino")
                destino.latitude = this.destino[0]
                destino.longitude = this.destino[1]

        // Calcular la distancia entre las ubicaciones en metros
                val distanciaEnMetros = origen.distanceTo(destino)

        // Convertir la distancia a otra unidad (por ejemplo, kilómetros)
                val distanciaEnKilometros = distanciaEnMetros / 1000

                println("Distancia en metros: $distanciaEnMetros")
                println("Distancia en kilómetros: $distanciaEnKilometros")
        return  distanciaEnKilometros

    }
}