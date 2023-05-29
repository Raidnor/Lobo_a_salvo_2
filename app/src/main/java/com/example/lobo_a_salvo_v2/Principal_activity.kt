package com.example.lobo_a_salvo_v2

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Principal_activity : AppCompatActivity() {

    //objeto de sincronizacion
    object Llamada_status{
        var estatus = false
        var tipo:String = ""
        var control=Phone(estatus,tipo, Principal_activity())
    }

    //boton llamar a seguridad
    private lateinit var button_llamar_seguridad: Button
    //boton llamar a salud
    private lateinit var button_llamar_salud: Button
    //boton mapas de los servicios
    private lateinit var button_mapas: ImageButton
    //boton manuales de ayuda
    private lateinit var button_manuales: ImageButton
    //boton manuales de usuario
    private lateinit var button_manual_user: ImageButton
    //boton reportar problemas
    private lateinit var button_report: ImageButton
    //boto perfil
    private lateinit var button_perfil: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        //Declaracion de los botones
            //boton llamar a seguridad
                 button_llamar_seguridad = findViewById<Button>(R.id.btn_seguridad)
            //boton llamar a salud
                 button_llamar_salud = findViewById<Button>(R.id.btn_salud)
            //boton mapas de los servicios
                 button_mapas = findViewById<ImageButton>(R.id.btn_mapas)
            //boton manuales de ayuda
                 button_manuales = findViewById<ImageButton>(R.id.btn_manuales)
            //boton manuales de usuario
                 button_manual_user = findViewById<ImageButton>(R.id.btn_manual_usuario)
            //boton reportar problemas
                 button_report = findViewById<ImageButton>(R.id.btn_report)
            //boto perfil
                 button_perfil = findViewById<ImageButton>(R.id.btn_perfil)

        //declarando las funciones
            llamar_seguridad()
            llamar_salud()
            ir_mapas()
            ir_manuales_ayuda()
            ir_manual_user()
            ir_report()
            ir_perfil()

    }


    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun mensaje(mensaje: String){
        Toast.makeText(this,mensaje,Toast.LENGTH_SHORT).show()
    }

     fun llamar_seguridad(){
        button_llamar_seguridad.setOnClickListener{
            val intent = Intent(this,Llamando::class.java)
            //pasando el objeto
            Llamada_status.estatus = true
            Llamada_status.tipo = "Seguridad"
            startActivity(intent)

        }
    }

    fun llamar_salud(){
        button_llamar_salud.setOnClickListener{
            mensaje("Llamando a salud")
            val intent = Intent(this,Llamando::class.java)
            //pasando el objeto
            Llamada_status.estatus = true
            Llamada_status.tipo = "Salud"
            startActivity(intent)
        }
    }

    fun ir_mapas(){
        button_mapas.setOnClickListener{
            mensaje("Mostrando mapas")
            val intent = Intent(this, Menu_mapas::class.java)
            startActivity(intent)
        }
    }

    fun ir_manuales_ayuda(){
        button_manuales.setOnClickListener{
            mensaje("Ir manuales ayuda")
            val intent = Intent(this,Menu_manuales::class.java)
            //intent.setDataAndType(Uri.parse("https://www.gob.mx/cms/uploads/attachment/file/783774/Manual_Primeros_Respondientes_v2_030321_compressed.pdf"), "application/pdf")
            startActivity(intent)
        }
    }

    fun ir_manual_user(){
        button_manual_user.setOnClickListener{
            mensaje("Ir manual usuario")
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setDataAndType(Uri.parse("https://correobuap-my.sharepoint.com/:b:/g/personal/victor_hueyo_alumno_buap_mx/EXKCochPWfNNilS18ueW5hgBN5DbAZJNJBAqmoEzQhbzCw?e=c9R3aJ"),"application/pdf")
            startActivity(intent)
        }
    }

    fun ir_report(){
        button_report.setOnClickListener{
            mensaje("Ir report")
        }
    }

    fun ir_perfil(){
        button_perfil.setOnClickListener{
            mensaje("Ir perfil")
        }
    }

}