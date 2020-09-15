package com.moralesjuan.yourticketapp.Categoria

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.moralesjuan.yourticketapp.Establecimiento.EstablecimientosFragment
import com.moralesjuan.yourticketapp.R

class CategoriaAdapter(
    //private val context: Context,
    private val fragmento: Fragment,
    private val lista_categorias: ArrayList<Categoria>,
    private val layout: Int
) : RecyclerView.Adapter<CategoriaAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(layout, parent, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.nombre_categoria.text = lista_categorias[position].nombre_cat
        var imagen = holder.itemView.findViewById<ImageView>(R.id.imageView_imagen)
        Glide.with(holder.itemView.context).load(lista_categorias[position].path_imagen)
            .into(imagen)
        cargarEstablecimiento(imagen, holder.nombre_categoria.text.toString())
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

    private fun cargarEstablecimiento(imagen: ImageView, nombre_categoria: String) {
        val nuevoFragmento = EstablecimientosFragment(nombre_categoria)
        imagen.setOnClickListener() {
            val transaction = fragmento.requireFragmentManager().beginTransaction()
            transaction.replace(fragmento.id, nuevoFragmento)
            transaction.addToBackStack(null)
            transaction.commit()
        }

    }
}