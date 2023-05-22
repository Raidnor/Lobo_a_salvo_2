package com.example.lobo_a_salvo_v2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.util.Patterns
import android.view.View
import android.widget.TextView


class Login_activity : AppCompatActivity() {

    private lateinit var editTextUsername: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonLogin: Button
    private lateinit var databaseHelper: SQLDB
    private lateinit var button_registrar: Button
    private var cont=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //datos para iniciar sesion
        editTextUsername = findViewById(R.id.text_email_reg)
        editTextPassword = findViewById(R.id.text_password_reg)
        buttonLogin = findViewById(R.id.btn_reg)
        //boton que aparece para registrarse
        button_registrar = findViewById(R.id.btn_ir_registrar)
        databaseHelper = SQLDB(this)

        buttonLogin.setOnClickListener {

            if(check_data()){
                val username = editTextUsername.text.toString()
                val password = editTextPassword.text.toString()
                if(isEmailValid(username)){
                            //comprobamos si existe el usuario
                            if (databaseHelper.checkUser(username, password)) {
                                // Iniciar sesión exitoso, hacer algo aquí
                                mensaje("Inicio correctamente")
                                val intent = Intent(this, Principal_activity::class.java)
                                startActivity(intent)
                                //finalizamos esta actividad
                                finish()
                            } else {
                                cont++;
                                mensaje("Inicio fallido")
                                // Credenciales inválidas, mostrar mensaje de error o realizar alguna acción
                                if(cont==3){
                                    findViewById<TextView>(R.id.text_info).visibility = View.VISIBLE
                                    findViewById<Button>(R.id.btn_ir_registrar).visibility = View.VISIBLE
                                }
                            }
                        }else{
                            mensaje("Correo no valido")
                        }
            }else{
                mensaje("Todos los campos deben de estar llenos")
            }




        }
        button_registrar.setOnClickListener{
            val intent = Intent(this, Registrar_activity::class.java)
            startActivity(intent)
            //finalizamos esta actividad
            finish()
        }
    }

    //comprobando si el correo es valido
    fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }


    //Comprobamos que los campos no esten vacios
    fun check_data():Boolean{
        var response = true
        if(editTextUsername.text.isEmpty()||editTextPassword.text.isEmpty()){
            response = false
        }

            return response
    }

    fun mensaje(mensaje:String){
        Toast.makeText(this,mensaje,Toast.LENGTH_SHORT).show()
    }
}
