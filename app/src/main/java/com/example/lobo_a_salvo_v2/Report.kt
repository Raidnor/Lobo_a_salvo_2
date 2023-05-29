package com.example.lobo_a_salvo_v2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

import android.Manifest
import android.content.pm.PackageManager
import android.os.Environment
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import java.io.File
import java.io.FileOutputStream

class Report : AppCompatActivity() {
    companion object {
        private const val REQUEST_PERMISSION_WRITE_EXTERNAL_STORAGE = 1
    }
    private lateinit var btn_report:Button
    private lateinit var report_text:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)

        btn_report = findViewById(R.id.btn_report_enviar)
        report_text = findViewById(R.id.report_text)

        btn_report.setOnClickListener{
            saveDataToFile()
        }
    }

    private fun saveDataToFile() {
        // Verificar si se tienen los permisos necesarios para escribir en almacenamiento externo
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Si no se tienen los permisos, solicitarlos al usuario
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                REQUEST_PERMISSION_WRITE_EXTERNAL_STORAGE
            )
        } else {
            // Si se tienen los permisos, guardar los datos en el archivo
            writeDataToFile()
        }
    }

    private fun writeDataToFile() {
        val data = report_text.text.toString()
        val fileName = "data.txt"
        val folderName = "my_folder" // Nombre de la carpeta dentro de la carpeta de la aplicación

        val folder = File(filesDir, folderName)
        if (!folder.exists()) {
            folder.mkdirs() // Crear la carpeta si no existe
        }

        val file = File(folder, fileName)

        try {
            FileOutputStream(file).use { outputStream ->
                outputStream.write(data.toByteArray())
                outputStream.close()
                // Mostrar mensaje de éxito
                showToast("Reporte enviado ")
            }
        } catch (e: Exception) {
            // Mostrar mensaje de error
            showToast("Error al enviar el reporte")
            e.printStackTrace()
        }
        report_text.setText("")
        //finish()
    }



    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}