package com.moralesjuan.yourticketapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.moralesjuan.yourticketapp.Cupones.Cupon
import com.moralesjuan.yourticketapp.Cupones.CuponAdapter
import com.moralesjuan.yourticketapp.InfoCupon.InformacionDelCuponFragment

class CuponesGuardadosFragment : Fragment() {

    private lateinit var cuponAdapter: CuponAdapter
    private lateinit var recyclerViewCupon: RecyclerView
    private var listaCupones = arrayListOf<Cupon>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var root = inflater.inflate(R.layout.fragment_cupones_guardados, container, false)

        root = cargarLista(root)

        return root
    }

    fun cargarLista(root: View): View {
        val db = FirebaseFirestore.getInstance()
        db.collection("cupon")
            .get()
            .addOnSuccessListener { documents ->
                for (documento in documents) {
                    val cupon = documento.toObject(Cupon::class.java)
                    cupon.id = documento.id
                    listaCupones.add(cupon)
                }
//                Toast.makeText(context, "Base de datos leida", Toast.LENGTH_SHORT).show()
                cuponAdapter = CuponAdapter(this, listaCupones, R.layout.row_cupon)
                recyclerViewCupon = root.findViewById(R.id.recyclerViewCuponesGuardados)
                recyclerViewCupon.layoutManager = LinearLayoutManager(context)
                recyclerViewCupon.adapter = cuponAdapter
            }
        return root
    }
}