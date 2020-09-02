package com.moralesjuan.yourticketapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import com.moralesjuan.yourticketapp.InfoCupon.InformacionDelCuponFragment

class InicioFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_inicio, container, false)
        val imageButton3: ImageButton = root.findViewById(R.id.imageButton3)
//        val nuevoFragmento = InformacionDelCuponFragment()
//        imageButton3.setOnClickListener() {
//            val transaction = requireFragmentManager().beginTransaction()
//            transaction.replace(R.id.fragment_inicio_xml, nuevoFragmento)
//            transaction.addToBackStack(null)
//            transaction.commit()
//        }
        return root
    }
}