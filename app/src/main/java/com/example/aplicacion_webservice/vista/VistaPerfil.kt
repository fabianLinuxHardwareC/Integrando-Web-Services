package com.example.aplicacion_webservice.vista

import com.example.aplicacion_webservice.modelo.Fotografia
import com.example.aplicacion_webservice.modelo.PublicacionPrueba

interface VistaPerfil {

    fun mostrarFotografias(fotografias: List<Fotografia>)

    fun mostrarPublicacion(publicacion: PublicacionPrueba)

    fun mostrarErrorServidor()

    fun mostrarErrorConexion()
}