package com.moralesjuan.yourticketapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment

class InformacionDelCuponFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_informacion_del_cupon, container, false)
        val buttonSaveCupon: Button = root.findViewById<Button>(R.id.buttonSaveCupon)
        buttonSaveCupon.setOnClickListener(){
            Toast.makeText( buttonSaveCupon.context , "@string/text_CuponSaved", Toast.LENGTH_LONG).show()
        }
        return root
    }
}