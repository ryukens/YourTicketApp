package com.moralesjuan.yourticketapp.Establecimiento

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.moralesjuan.yourticketapp.InfoEstablecimiento.InfoEstablecimientosFragment
import com.moralesjuan.yourticketapp.R

class EstablecimientoAdapter(
    //private val context: Context,
    private val fragmento: Fragment,
    private val lista_establecimientos: ArrayList<Establecimiento>,
    private val layout: Int
) : RecyclerView.Adapter<EstablecimientoAdapter.ViewHolder>() {

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
        holder.nombre_establecimiento.text = lista_establecimientos[position].nombre_est
        var imagen = holder.itemView.findViewById<ImageView>(R.id.imageView_imagen)
        Glide.with(holder.itemView.context).load(lista_establecimientos[position].path_est).into(imagen)
        cargarInfoEstablecimiento(imagen, holder.nombre_establecimiento.text.toString())
    }

    override fun getItemCount(): Int {
        return lista_establecimientos.size
    }

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        var nombre_establecimiento: TextView
        var imagen_establecimiento: ImageView

        init {
            nombre_establecimiento = itemView.findViewById<View>(R.id.textView_titulo) as TextView
            imagen_establecimiento = itemView.findViewById<View>(R.id.imageView_imagen) as ImageView
        }
    }

    private fun cargarInfoEstablecimiento(imagen: ImageView, nombre_establecimiento: String) {
        val nuevoFragmento = InfoEstablecimientosFragment(nombre_establecimiento)
        imagen.setOnClickListener() {
            val transaction = fragmento.requireFragmentManager().beginTransaction()
            transaction.add(fragmento.id, nuevoFragmento)
            transaction.addToBackStack(null)
            transaction.commit()
        }

    }
}