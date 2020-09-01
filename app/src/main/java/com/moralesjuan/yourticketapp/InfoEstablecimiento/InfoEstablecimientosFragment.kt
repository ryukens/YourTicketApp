package com.moralesjuan.yourticketapp.InfoEstablecimiento

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.moralesjuan.yourticketapp.Establecimiento.Establecimiento
import com.moralesjuan.yourticketapp.InfoCupon.InformacionDelCuponFragment
import com.moralesjuan.yourticketapp.R

class InfoEstablecimientosFragment(private var nombre_establecimiento: String) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_info_establecimientos, container, false)

        val textViewEstName: TextView = root.findViewById(R.id.textViewEstablishmentTitle)
        textViewEstName.text = nombre_establecimiento
        cargarInfo(root)

//        val imageView_Cupon1: ImageView = root.findViewById(R.id.imageViewCupon1)
//        val nuevoFragmento =
//            InformacionDelCuponFragment()
//        imageView_Cupon1.setOnClickListener(){
//            val transaction = requireFragmentManager().beginTransaction()
//            transaction.add(R.id.fragment_informacion_establecimiento_xml, nuevoFragmento)
//            transaction.addToBackStack(null)
//            transaction.commit()
//        }
        return root
    }

    fun cargarInfo(root: View) {
        lateinit var establecimiento: Establecimiento
        val db = FirebaseFirestore.getInstance()
        db.collection("establecimiento")
            .whereEqualTo("nombre_est", nombre_establecimiento)
            .get()
            .addOnSuccessListener { documents ->
                for (documento in documents) {
                    establecimiento = documento.toObject(Establecimiento::class.java)
                }
//                Toast.makeText(context, "Base de datos leida", Toast.LENGTH_SHORT).show()

                cargarCupon(root, establecimiento)
            }
    }

    fun cargarCupon(root: View, establecimiento: Establecimiento) {
        val imageView_Cupon1: ImageView = root.findViewById(R.id.imageViewCupon1)
        lateinit var cuponid: String
        val db = FirebaseFirestore.getInstance()
        db.collection("cupon")
            .whereEqualTo("establecimiento", establecimiento.nombre_est)
            .get()
            .addOnSuccessListener { documents ->
                for (documento in documents) {
//                    establecimiento = documento.toObject(Establecimiento::class.java)
                    cuponid = documento.id
                    Glide.with(this).load(documento["path_cupon"]).into(imageView_Cupon1)
                }
//                Toast.makeText(context, "Cupon leido", Toast.LENGTH_SHORT).show()
                try {
                    val nuevoFragmento = InformacionDelCuponFragment(cuponid)
                    imageView_Cupon1.setOnClickListener() {
                        val transaction = requireFragmentManager().beginTransaction()
                        transaction.add(this.id, nuevoFragmento)
                        transaction.addToBackStack(null)
                        transaction.commit()
                    }
                } catch (e: Exception) {
                    Toast.makeText(context, "NO HAY CUPONES", Toast.LENGTH_SHORT).show()
                }
            }
    }
}