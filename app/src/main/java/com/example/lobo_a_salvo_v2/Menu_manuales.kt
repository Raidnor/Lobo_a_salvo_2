package com.example.lobo_a_salvo_v2

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Im
import android.widget.ImageButton

class Menu_manuales : AppCompatActivity() {
    private lateinit var incendio: ImageButton
    private lateinit var pAuxilios: ImageButton
    private lateinit var volcan:ImageButton

    private lateinit var sismo:ImageButton
    private lateinit var intoxicacion:ImageButton
    private lateinit var heridaP:ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_manuales)

        incendio = findViewById<ImageButton>(R.id.btn_incendio)
        pAuxilios = findViewById<ImageButton>(R.id.btn_pAuxilios)
        volcan = findViewById<ImageButton>(R.id.btn_volcan)

        sismo = findViewById<ImageButton>(R.id.btn_sismo)
        intoxicacion = findViewById<ImageButton>(R.id.btn_intoxicacion)
        heridaP = findViewById<ImageButton>(R.id.btn_heridaP)

        incendio.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW)
            val filePath = "R.raw.en_caso_de_incendio"
            // Crear un objeto Uri a partir de la ruta del archivo
            val fileUri = Uri.parse(filePath)
            intent.setDataAndType(Uri.parse("https://www.proteccioncivil.cdmx.gob.mx/storage/app/uploads/public/61f/184/cb4/61f184cb43402896280457.pdf"), "application/pdf")
            startActivity(intent)
        }

        pAuxilios.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setDataAndType(Uri.parse("https://www.gob.mx/cms/uploads/attachment/file/783774/Manual_Primeros_Respondientes_v2_030321_compressed.pdf"), "application/pdf")
            startActivity(intent)
        }
        volcan.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setDataAndType(Uri.parse("https://www.proteccioncivil.cdmx.gob.mx/storage/app/uploads/public/61f/184/cb4/61f184cb43402896280457.pdf"), "application/pdf")
            startActivity(intent)
        }
        sismo.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setDataAndType(Uri.parse("https://www.cenapred.unam.mx/es/Publicaciones/archivos/258-INFOGRAFAENCASODESISMO.PDF"), "application/pdf")
            startActivity(intent)
        }
        intoxicacion.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setDataAndType(Uri.parse("https://docs.bvsalud.org/biblioref/2022/01/1354120/guiaclinicaparalaatenciondepersonasconintoxicaciones-acuerdo-2968-v1.pdf"), "application/pdf")
            startActivity(intent)
        }
        heridaP.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setDataAndType(Uri.parse("https://fm.uacam.mx/view/download?file=174/adjuntos/CURACION_DE_HERIDAS.pdf&tipo=paginas"), "application/pdf")
            startActivity(intent)
        }
    }
}