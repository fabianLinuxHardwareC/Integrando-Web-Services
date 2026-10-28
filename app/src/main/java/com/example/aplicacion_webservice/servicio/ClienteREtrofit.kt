package com.example.aplicacion_webservice.servicio

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ClienteRetrofit {

    val servicio: ServicioWeb = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ServicioWeb::class.java)

}