package com.example.aplicacion_webservice.vista

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.aplicacion_webservice.R
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class ConfiguracionActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.pantalla_configurar_cuenta)

        val barra = findViewById<MaterialToolbar>(R.id.barraConfiguracion)
        val campoUsuario = findViewById<TextInputLayout>(R.id.campoUsuario)
        val entradaUsuario = findViewById<TextInputEditText>(R.id.entradaUsuario)
        val botonGuardar = findViewById<MaterialButton>(R.id.botonGuardarCuenta)
        val preferencias = getSharedPreferences("Petagram", MODE_PRIVATE)

        entradaUsuario.setText(preferencias.getString("usuario", ""))

        botonGuardar.setOnClickListener {

            val usuario = entradaUsuario.text.toString().trim()

            if (usuario.isEmpty()) {
                campoUsuario.error = getString(R.string.mensaje_usuario_vacio)
            } else {
                campoUsuario.error = null
                preferencias.edit().putString("usuario", usuario).apply()

                Toast.makeText(
                    this,
                    R.string.mensaje_usuario_guardado,
                    Toast.LENGTH_SHORT
                ).show()

                finish()
            }
        }

        barra.setNavigationOnClickListener {
            finish()
        }

        barra.setOnMenuItemClickListener { opcion ->

            when (opcion.itemId) {

                R.id.menuVolver -> {
                    finish()
                    true
                }

                R.id.menuAcerca -> {
                    MaterialAlertDialogBuilder(this)
                        .setTitle(R.string.menu_acerca)
                        .setMessage(R.string.mensaje_acerca)
                        .setPositiveButton(R.string.boton_aceptar, null)
                        .show()
                    true
                }

                else -> false
            }
        }
    }
}