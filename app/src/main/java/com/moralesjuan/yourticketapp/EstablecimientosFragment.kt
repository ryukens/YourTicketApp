package com.moralesjuan.yourticketapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment

class EstablecimientosFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_establecimientos, container, false)
        val imageView_Est_Taty: ImageView = root.findViewById(R.id.imageView_Est_Taty)
        val imageView_Est_Eta: ImageView = root.findViewById(R.id.imageView_Est_Etafa)
        val imageView_Est_Naf: ImageView = root.findViewById(R.id.imageView_Est_Naf)
        val imageView_Est_Forever: ImageView = root.findViewById(R.id.imageView_Est_Forever)
        val imageView_Est_Super: ImageView = root.findViewById(R.id.imageView_Est_Super)
        val nuevoFragmento = InfoEstablecimientosFragment()
        imageView_Est_Taty.setOnClickListener(){
            val transaction = requireFragmentManager().beginTransaction()
            transaction.add(R.id.fragment_establecimientos_xml, nuevoFragmento)
            transaction.addToBackStack(null)
            transaction.commit()
        }
        return root
    }
}