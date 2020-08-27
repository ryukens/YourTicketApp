package com.moralesjuan.yourticketapp.Establecimiento

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.moralesjuan.yourticketapp.R

class EstablecimientoAdapter(
    //private val context: Context,
    private val fragmento: Fragment,
    private val lista_categorias: ArrayList<Establecimiento>,
    private val layout: Int
) : RecyclerView.Adapter<EstablecimientoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ViewHolder(
            v
        )
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.nombre_categoria.text = lista_categorias[position].nombre_est
        var imagen = holder.itemView.findViewById<ImageView>(R.id.imageView_imagen)
        Glide.with(holder.itemView.context).load(lista_categorias[position].path_est).into(imagen)
    }

    override fun getItemCount(): Int {
        return lista_categorias.size
    }

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        var nombre_categoria: TextView
        var imagen_categoria: ImageView

        init {
            nombre_categoria = itemView.findViewById<View>(R.id.textView_titulo) as TextView
            imagen_categoria = itemView.findViewById<View>(R.id.imageView_imagen) as ImageView
        }
    }
}