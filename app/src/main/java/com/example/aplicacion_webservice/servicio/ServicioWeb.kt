package com.example.aplicacion_webservice.servicio

import com.example.aplicacion_webservice.modelo.PublicacionPrueba
import retrofit2.Call
import retrofit2.http.GET

interface ServicioWeb {

    @GET("posts/1")
    fun obtenerPublicacion(): Call<PublicacionPrueba>

}