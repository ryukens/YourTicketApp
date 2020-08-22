package com.moralesjuan.yourticketapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CategoriaAdapter(
    //private val context: Context,
    private val lista_categorias: ArrayList<Categoria>,
    private val layout: Int
) : RecyclerView.Adapter<CategoriaAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        //Toast.makeText(parent.context, "entra aca", Toast.LENGTH_LONG).show()
        return ViewHolder(v)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.nombre_categoria.text = lista_categorias[position].nombre_cat
        //holder.imagen_categoria.setImageResource(R.drawable.img_cine)
        var imagen = holder.itemView.findViewById<ImageView>(R.id.imageView_imagen)
        Glide.with(holder.itemView.context).load(lista_categorias[position].path_imagen).into(imagen)
//        val ejemplo_categoria = holder.itemView.findViewById<ImageView>(R.id.ejemplo_categoria)
//        holder.imageViewCategoria.setImageResource(R.drawable.img_ropa)
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