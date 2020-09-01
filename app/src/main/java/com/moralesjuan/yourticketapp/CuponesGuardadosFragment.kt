package com.moralesjuan.yourticketapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.moralesjuan.yourticketapp.InfoCupon.InformacionDelCuponFragment

class CuponesGuardadosFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_cupones_guardados, container, false)
        val imageView_Cupon1: ImageView = root.findViewById(R.id.imageView_Cupon1)
//        val nuevoFragmento = InformacionDelCuponFragment()
//        imageView_Cupon1.setOnClickListener() {
//            val transaction = requireFragmentManager().beginTransaction()
//            transaction.add(R.id.fragment_cupones_guardados_xml, nuevoFragmento)
//            transaction.addToBackStack(null)
//            transaction.commit()
//        }
        return root
    }
}