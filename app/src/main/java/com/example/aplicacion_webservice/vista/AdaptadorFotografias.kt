package com.example.aplicacion_webservice.vista

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aplicacion_webservice.R
import com.example.aplicacion_webservice.modelo.Fotografia

class AdaptadorFotografias(
    private val fotografias: List<Fotografia>
) : RecyclerView.Adapter<AdaptadorFotografias.VistaFotografia>() {

    class VistaFotografia(vista: View) : RecyclerView.ViewHolder(vista) {

        val imagen = vista.findViewById<ImageView>(R.id.imagenFotografia)

        val huesos = vista.findViewById<TextView>(R.id.textoCantidadHuesos)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VistaFotografia {

        val vista = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_fotografia, parent, false)

        return VistaFotografia(vista)
    }

    override fun onBindViewHolder(holder: VistaFotografia, position: Int) {

        val fotografia = fotografias[position]

        if (fotografia.url != null) {

            Glide.with(holder.imagen)
                .load(fotografia.url)
                .centerCrop()
                .placeholder(R.drawable.ic_mascota)
                .error(R.drawable.ic_mascota)
                .into(holder.imagen)

        } else {

            Glide.with(holder.imagen)
                .load(fotografia.imagen)
                .fitCenter()
                .into(holder.imagen)
        }

        holder.huesos.text = fotografia.huesos.toString()
    }

    override fun getItemCount(): Int {

        return fotografias.size
    }
}