package com.example.aplicacion_webservice.servicio

import com.example.aplicacion_webservice.modelo.RespuestaMascotas
import retrofit2.Call
import retrofit2.http.GET

interface ServicioMascotas {

    @GET("breeds/image/random/9")
    fun obtenerMascotas(): Call<RespuestaMascotas>

}