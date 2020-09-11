package com.moralesjuan.yourticketapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.moralesjuan.yourticketapp.Cupones.Cupon
import com.moralesjuan.yourticketapp.Cupones.CuponAdapter

class InicioFragment : Fragment() {

    private lateinit var cuponAdapter: CuponAdapter
    private lateinit var recyclerViewCupon: RecyclerView
    private var listaCupones = arrayListOf<Cupon>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var root = inflater.inflate(R.layout.fragment_inicio, container, false)

        root = cargarLista(root)

//        val imageButton3: ImageButton = root.findViewById(R.id.imageView10)
//        val nuevoFragmento = InformacionDelCuponFragment()
//        imageButton3.setOnClickListener() {
//            val transaction = requireFragmentManager().beginTransaction()
//            transaction.replace(R.id.fragment_inicio_xml, nuevoFragmento)
//            transaction.addToBackStack(null)
//            transaction.commit()
//        }

        return root
    }

    fun cargarLista(root: View): View {
        lateinit var cupon_id : String
        val db = FirebaseFirestore.getInstance()
        db.collection("cupon")
            .get()
            .addOnSuccessListener { documents ->
                for (documento in documents) {
                    val cupon = documento.toObject(Cupon::class.java)
                    cupon_id = documento.id
                    listaCupones.add(cupon)
                }
//                Toast.makeText(context, "Base de datos leida", Toast.LENGTH_SHORT).show()

                cuponAdapter = CuponAdapter(this, listaCupones, R.layout.row_cupon, cupon_id)
                recyclerViewCupon = root.findViewById(R.id.recyclerViewCupon)
                recyclerViewCupon.layoutManager = LinearLayoutManager(context)
                recyclerViewCupon.adapter = cuponAdapter

            }
        return root
    }

}