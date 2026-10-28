package com.example.aplicacion_webservice.vista

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.RecyclerView
import com.example.aplicacion_webservice.R
import com.example.aplicacion_webservice.modelo.Fotografia
import com.example.aplicacion_webservice.modelo.PublicacionPrueba
import com.example.aplicacion_webservice.presentador.PresentadorPerfil
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.floatingactionbutton.FloatingActionButton

class PerfilActivity : ComponentActivity(), VistaPerfil {

    private lateinit var presentador: PresentadorPerfil

    private val tomarFoto = registerForActivityResult(
        ActivityResultContracts.TakePicturePreview()
    ) { foto ->

        if (foto != null) {

            val vistaFoto = ImageView(this)
            vistaFoto.setImageBitmap(foto)
            vistaFoto.adjustViewBounds = true

            MaterialAlertDialogBuilder(this)
                .setTitle(R.string.titulo_fotografia)
                .setView(vistaFoto)
                .setPositiveButton(R.string.boton_aceptar, null)
                .show()

        } else {

            Toast.makeText(
                this,
                R.string.mensaje_camara,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.pantalla_perfil)

        val barra = findViewById<MaterialToolbar>(R.id.barraNavegador)
        val botonCamara = findViewById<FloatingActionButton>(R.id.botonCamara)

        barra.setOnMenuItemClickListener { opcion ->

            when (opcion.itemId) {

                R.id.menuConfigurar -> {
                    val ventana = Intent(this, ConfiguracionActivity::class.java)
                    startActivity(ventana)
                    true
                }

                R.id.menuContacto -> {
                    MaterialAlertDialogBuilder(this)
                        .setTitle(R.string.menu_contacto)
                        .setMessage(R.string.mensaje_contacto)
                        .setPositiveButton(R.string.boton_aceptar, null)
                        .show()
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

                R.id.menuInicio -> true

                else -> false
            }
        }

        botonCamara.setOnClickListener {
            tomarFoto.launch(null)
        }

        presentador = PresentadorPerfil(this)
        presentador.cargarDatos()
    }

    override fun mostrarFotografias(fotografias: List<Fotografia>) {
        val listaFotografias = findViewById<RecyclerView>(R.id.listaFotografias)
        val adaptador = AdaptadorFotografias(fotografias)
        listaFotografias.adapter = adaptador
    }

    override fun mostrarPublicacion(publicacion: PublicacionPrueba) {
        Toast.makeText(
            this,
            getString(R.string.mensaje_conexion_ok, publicacion.id),
            Toast.LENGTH_LONG
        ).show()

        Log.d("PetagramAPI", "Título: ${publicacion.title}")
    }

    override fun mostrarErrorServidor() {
        Toast.makeText(
            this,
            R.string.mensaje_error_servidor,
            Toast.LENGTH_LONG
        ).show()
    }

    override fun mostrarErrorConexion() {
        Toast.makeText(
            this,
            R.string.mensaje_error_conexion,
            Toast.LENGTH_LONG
        ).show()
    }

    override fun onResume() {
        super.onResume()

        val preferencias = getSharedPreferences("Petagram", MODE_PRIVATE)
        val usuario = preferencias.getString("usuario", "")
        val textoNombre = findViewById<TextView>(R.id.textoNombreUsuario)

        textoNombre.text = if (usuario.isNullOrEmpty()) {
            getString(R.string.nombre_perfil)
        } else {
            usuario
        }
    }

    override fun onDestroy() {
        presentador.detener()
        super.onDestroy()
    }
}