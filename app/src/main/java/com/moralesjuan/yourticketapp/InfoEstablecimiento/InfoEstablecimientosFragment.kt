package com.moralesjuan.yourticketapp.InfoEstablecimiento

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.moralesjuan.yourticketapp.Cupones.Cupon
import com.moralesjuan.yourticketapp.Cupones.CuponAdapter
import com.moralesjuan.yourticketapp.Establecimiento.Establecimiento
import com.moralesjuan.yourticketapp.InfoCupon.InformacionDelCuponFragment
import com.moralesjuan.yourticketapp.R

class InfoEstablecimientosFragment(private var nombre_establecimiento: String) : Fragment() {

    private lateinit var cuponAdapter: CuponAdapter
    private lateinit var recyclerViewCupon: RecyclerView
    private var listaCupones = arrayListOf<Cupon>()

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

    fun cargarCupon(root: View, establecimiento: Establecimiento): View {
        // 1     val imageView_Cupon1: ImageView = root.findViewById(R.id.imageViewCupon1)
        lateinit var cuponid: String
        val db = FirebaseFirestore.getInstance()

//        try {


            db.collection("cupon")
                .whereEqualTo("establecimiento", establecimiento.nombre_est)
                .get()
                .addOnSuccessListener { documents ->
                    for (documento in documents) {
//                    establecimiento = documento.toObject(Establecimiento::class.java)
                        val cupon = documento.toObject(Cupon::class.java)
                        cuponid = documento.id
                        listaCupones.add(cupon)
// 1                   Glide.with(this).load(documento["path_cupon"]).into(imageView_Cupon1)
                    }
                    cuponAdapter = CuponAdapter(this, listaCupones, R.layout.row_cupon, cuponid)
                    recyclerViewCupon = root.findViewById(R.id.recyclerViewCuponEstablecimiento)
                    recyclerViewCupon.layoutManager = LinearLayoutManager(context)
                    recyclerViewCupon.adapter = cuponAdapter

                }
//        } catch (e: Exception) {
//            Toast.makeText(context, "NO HAY CUPONES", Toast.LENGTH_SHORT).show()
//        }
            return root
    }

}