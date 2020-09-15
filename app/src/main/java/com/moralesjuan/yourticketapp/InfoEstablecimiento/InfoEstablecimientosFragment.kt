package com.moralesjuan.yourticketapp.InfoEstablecimiento

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.moralesjuan.yourticketapp.Cupones.Cupon
import com.moralesjuan.yourticketapp.Cupones.CuponAdapter
import com.moralesjuan.yourticketapp.Establecimiento.Establecimiento
import com.moralesjuan.yourticketapp.MapsFragment
import com.moralesjuan.yourticketapp.R
import kotlinx.android.synthetic.main.fragment_info_establecimientos.view.*

class InfoEstablecimientosFragment(private var nombre_establecimiento: String) : Fragment() {

    private lateinit var cuponAdapter: CuponAdapter
    private lateinit var recyclerViewCupon: RecyclerView
    private var listaCupones = arrayListOf<Cupon>()

    private lateinit var fragmentGoogle: Fragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_info_establecimientos, container, false)

        val textViewEstName: TextView = root.findViewById(R.id.textViewEstablishmentTitle)
        textViewEstName.text = nombre_establecimiento
        cargarInfo(root)

        val adRequest = AdRequest.Builder().build()
        root.adViewInfoEstablecimiento.loadAd(adRequest)

        fragmentGoogle = MapsFragment(textViewEstName.text as String)
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.fragment2,fragmentGoogle)
            ?.commit()
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

        val db = FirebaseFirestore.getInstance()

//        try {
            db.collection("cupon")
                .whereEqualTo("establecimiento", establecimiento.nombre_est)
                .get()
                .addOnSuccessListener { documents ->
                    for (documento in documents) {
                        val cupon = documento.toObject(Cupon::class.java)
                        cupon.id = documento.id
                        listaCupones.add(cupon)
                    }
                    cuponAdapter = CuponAdapter(this, listaCupones, R.layout.row_cupon)
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