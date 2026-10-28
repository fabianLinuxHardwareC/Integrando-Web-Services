package com.example.aplicacion_webservice.servicio

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ClienteMascotas {

    val servicio: ServicioMascotas = Retrofit.Builder()
        .baseUrl("https://dog.ceo/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ServicioMascotas::class.java)

}