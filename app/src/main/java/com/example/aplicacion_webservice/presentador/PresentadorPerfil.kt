package com.example.aplicacion_webservice.presentador

import com.example.aplicacion_webservice.modelo.RepositorioPerfil
import com.example.aplicacion_webservice.vista.VistaPerfil

class PresentadorPerfil(private val vista: VistaPerfil) {

    private val modelo = RepositorioPerfil()

    fun cargarDatos() {

        val fotografias = modelo.obtenerFotografias()

        vista.mostrarFotografias(fotografias)

        modelo.obtenerMascotas(

            correcto = { mascotas ->

                vista.mostrarFotografias(mascotas)
            },

            error = {

                vista.mostrarErrorConexion()
            }
        )

        modelo.obtenerPublicacion(

            correcto = { publicacion ->

                vista.mostrarPublicacion(publicacion)
            },

            errorServidor = {

                vista.mostrarErrorServidor()
            },

            errorConexion = {

                vista.mostrarErrorConexion()
            }
        )
    }

    fun detener() {

        modelo.cancelarConsulta()
    }
}