package com.example.aplicacion_webservice

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.aplicacion_webservice.vista.PerfilActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.menu_bienvenida)

        val botonIniciar = findViewById<MaterialButton>(R.id.botonIniciar)

        val botonCreditos = findViewById<MaterialButton>(R.id.botonCreditos)

        botonIniciar.setOnClickListener {

            val ventana = Intent(this, PerfilActivity::class.java)

            startActivity(ventana)
        }

        botonCreditos.setOnClickListener {

            MaterialAlertDialogBuilder(this)
                .setTitle(R.string.texto_creditos)
                .setMessage(R.string.mensaje_autor)
                .setPositiveButton(R.string.boton_aceptar, null)
                .show()
        }
    }
}