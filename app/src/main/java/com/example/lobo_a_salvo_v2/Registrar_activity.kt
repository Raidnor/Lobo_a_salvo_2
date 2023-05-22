package com.example.lobo_a_salvo_v2

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock.sleep
import android.text.BoringLayout
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast

class Registrar_activity : AppCompatActivity() {

    private lateinit var correo: EditText
    private lateinit var password: EditText
    private lateinit var confirm: EditText
    private lateinit var registrar: Button
    private lateinit var databaseHelper: SQLDB
    private lateinit var check_correo: ImageView
    private lateinit var check_pass: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        //datos a registrar
        correo = findViewById(R.id.correo_reg)
        password = findViewById(R.id.password_reg)
        confirm = findViewById(R.id.password_reg_confirm)
        var correo_confirm:Boolean = false
        //base de datos
        databaseHelper = SQLDB(this)

        //boton para registrar
        registrar= findViewById(R.id.btn_registrar)

        //imagenes de verificacion
        check_correo = findViewById<ImageView>(R.id.verifi_correo)
        check_pass = findViewById<ImageView>(R.id.verifi_pass)

        correo.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Se llama antes de que el texto cambie
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Se llama cuando el texto cambia
                val newText = s.toString()
                // Realiza las acciones necesarias con el nuevo texto
                if (isEmailValid(newText)){

                    //si devuelve verdadero es que ya existe el correo
                    if(databaseHelper.checkCorreo(newText)){
                        correo_confirm = false
                        mensaje("Correo ya registrado")
                        //asignamos el icono correspondiente
                        check_correo.setImageResource(R.drawable.ic_baseline_close_24)
                        //mostramos el elemento
                        check_correo.visibility = View.VISIBLE

                    }else{
                        correo_confirm = true
                        //mensaje("correo disponible")
                        check_correo.setImageResource(R.drawable.ic_baseline_check_24)
                        //mostramos el elemento
                        check_correo.visibility = View.VISIBLE
                    }
                }else{
                    //asignamos el icono correspondiente
                    check_correo.setImageResource(R.drawable.ic_baseline_close_24)
                    //mostramos el elemento
                    check_correo.visibility = View.VISIBLE
                    mensaje("Formato incorrecto")
                }
            }

            override fun afterTextChanged(s: Editable?) {
                // Se llama después de que el texto cambie
            }
        })


        registrar.setOnClickListener{
            var user = correo.text.toString()
            var pass = password.text.toString()
            var pass_confirm = confirm.text.toString()

            if(check_data()){
                if (correo_confirm){
                    if (check_pass()){
                        //obtenemos y mostramos el icono
                        check_pass.setImageResource(R.drawable.ic_baseline_check_24)
                        check_pass.visibility = View.VISIBLE

                        databaseHelper.addUser(user,pass)
                        //verificamos si se creo de forma correcta el usuario
                        if(databaseHelper.checkUser(user,pass)){
                            mensaje("Usuario creado exitosamente")
                            sleep(4)
                            val intent = Intent(this, Login_activity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }else{
                        check_pass.setImageResource(R.drawable.ic_baseline_close_24)
                        check_pass.visibility = View.VISIBLE
                        mensaje("Contraseñas no coinciden")
                    }
                }
            }
        }



    }

    //comprobando si el correo es valido
    fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }


    //Comprobamos que los campos no esten vacios
    fun check_data():Boolean{
        var response = true
        if(correo.text.isEmpty()||password.text.isEmpty()||confirm.text.isEmpty()){
            response = false
            mensaje("Todos los campos deben de estar llenos")
        }

        return response
    }


    fun check_pass():Boolean{
        var response = false
            if (password.text.toString()==confirm.text.toString()){
                response = true
            }
        return response
    }

    fun mensaje(mensaje:String){
        Toast.makeText(this,mensaje, Toast.LENGTH_SHORT).show()
    }
}