package com.moralesjuan.yourticketapp


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment


class CategoriasFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_categorias, container, false)
        val ImageView_Cat_Comida: ImageView = root.findViewById(R.id.imageView_Cat_Comida)
        val ImageView_Cat_Ropa: ImageView = root.findViewById(R.id.imageView_Cat_Ropa)
        val ImageView_Cat_Articulos: ImageView = root.findViewById(R.id.imageView_Cat_Articulos)
        val ImageView_Cat_Cine: ImageView = root.findViewById(R.id.imageView_Cat_Cine)
        val ImageView_Cat_Musica: ImageView = root.findViewById(R.id.imageView_Cat_Musica)
        val nuevoFragmento = EstablecimientosFragment()
        ImageView_Cat_Comida.setOnClickListener(){
            val transaction = requireFragmentManager().beginTransaction()
            transaction.add(R.id.fragment_categorias_xml, nuevoFragmento)
            transaction.addToBackStack(null)
            transaction.commit()
        }
        ImageView_Cat_Ropa.setOnClickListener(){
            val transaction = requireFragmentManager().beginTransaction()
            transaction.replace(R.id.fragment_categorias_xml, nuevoFragmento)
            transaction.addToBackStack(null)
            transaction.commit()
        }
        return root
    }
}