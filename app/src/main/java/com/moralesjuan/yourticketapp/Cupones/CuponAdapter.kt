package com.moralesjuan.yourticketapp.Cupones

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.moralesjuan.yourticketapp.Establecimiento.EstablecimientosFragment
import com.moralesjuan.yourticketapp.InfoCupon.InformacionDelCuponFragment
import com.moralesjuan.yourticketapp.InicioFragment
import com.moralesjuan.yourticketapp.R


class CuponAdapter (
    //private val context: Context,
    private val fragmento: Fragment,
    private val lista_cupones: ArrayList<Cupon>,
    private val layout: Int,
    private val cupon_id : String
) : RecyclerView.Adapter<CuponAdapter.ViewHolder>() {

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
        holder.titulo_promocion.text = lista_cupones[position].promocion
        var imagen = holder.itemView.findViewById<ImageView>(R.id.imageView_imagen_cupon)
        Glide.with(holder.itemView.context).load(lista_cupones[position].path_cupon)
            .into(imagen)
        cargarInfoCupones(imagen)

    }

    override fun getItemCount(): Int {
        return lista_cupones.size
    }

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        var titulo_promocion: TextView
        var imagen_cupon: ImageView

        init {
            titulo_promocion = itemView.findViewById<View>(R.id.textView_titulo_cupon) as TextView
            imagen_cupon = itemView.findViewById<View>(R.id.imageView_imagen_cupon) as ImageView
        }
    }

    private fun cargarInfoCupones(imagen: ImageView) {
        val nuevoFragmento = InformacionDelCuponFragment(cupon_id)
        imagen.setOnClickListener() {
            val transaction = fragmento.requireFragmentManager().beginTransaction()
            transaction.add(fragmento.id, nuevoFragmento)
            transaction.addToBackStack(null)
            transaction.commit()
        }

    }


}