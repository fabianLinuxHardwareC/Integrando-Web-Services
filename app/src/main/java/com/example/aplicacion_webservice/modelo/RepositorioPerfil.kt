package com.example.aplicacion_webservice.modelo

import android.util.Log
import com.example.aplicacion_webservice.R
import com.example.aplicacion_webservice.servicio.ClienteRetrofit
import com.example.aplicacion_webservice.servicio.ClienteMascotas
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositorioPerfil {

    private var llamadaPublicacion: Call<PublicacionPrueba>? = null

    private var llamadaMascotas: Call<RespuestaMascotas>? = null

    fun obtenerFotografias(): List<Fotografia> {

        return listOf(
            Fotografia(R.drawable.ic_mascota, 5),
            Fotografia(R.drawable.ic_mascota, 0),
            Fotografia(R.drawable.ic_mascota, 3),
            Fotografia(R.drawable.ic_mascota, 10),
            Fotografia(R.drawable.ic_mascota, 2),
            Fotografia(R.drawable.ic_mascota, 3),
            Fotografia(R.drawable.ic_mascota, 8),
            Fotografia(R.drawable.ic_mascota, 4),
            Fotografia(R.drawable.ic_mascota, 6)
        )
    }

    fun obtenerMascotas(
        correcto: (List<Fotografia>) -> Unit,
        error: () -> Unit
    ) {

        llamadaMascotas = ClienteMascotas.servicio.obtenerMascotas()

        llamadaMascotas?.enqueue(object : Callback<RespuestaMascotas> {

            override fun onResponse(
                call: Call<RespuestaMascotas>,
                response: Response<RespuestaMascotas>
            ) {

                val datos = response.body()

                if (response.isSuccessful &&
                    datos != null &&
                    datos.status == "success" &&
                    datos.message.isNotEmpty()) {

                    val huesos = listOf(5, 0, 3, 10, 2, 3, 8, 4, 6)

                    val fotografias = datos.message.mapIndexed { indice, url ->

                        Fotografia(
                            R.drawable.ic_mascota,
                            huesos[indice % huesos.size],
                            url
                        )
                    }

                    Log.d(
                        "PetagramFotos",
                        "Fotografías recibidas: ${fotografias.size}"
                    )

                    correcto(fotografias)

                } else {

                    Log.e("PetagramFotos", "Respuesta inválida del servidor")

                    error()
                }
            }

            override fun onFailure(
                call: Call<RespuestaMascotas>,
                fallo: Throwable
            ) {

                if (!call.isCanceled) {

                    Log.e("PetagramFotos", "Error de conexión", fallo)

                    error()
                }
            }
        })
    }

    fun obtenerPublicacion(
        correcto: (PublicacionPrueba) -> Unit,
        errorServidor: () -> Unit,
        errorConexion: () -> Unit
    ) {

        llamadaPublicacion = ClienteRetrofit.servicio.obtenerPublicacion()

        llamadaPublicacion?.enqueue(object : Callback<PublicacionPrueba> {

            override fun onResponse(
                call: Call<PublicacionPrueba>,
                response: Response<PublicacionPrueba>
            ) {

                val publicacion = response.body()

                if (response.isSuccessful && publicacion != null) {

                    correcto(publicacion)

                } else {

                    errorServidor()
                }
            }

            override fun onFailure(
                call: Call<PublicacionPrueba>,
                fallo: Throwable
            ) {

                if (!call.isCanceled) {

                    Log.e("PetagramAPI", "Error de conexión", fallo)

                    errorConexion()
                }
            }
        })
    }

    fun cancelarConsulta() {

        llamadaPublicacion?.cancel()

        llamadaMascotas?.cancel()
    }
}