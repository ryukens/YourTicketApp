package com.moralesjuan.yourticketapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.moralesjuan.yourticketapp.Cupones.Cupon
import com.moralesjuan.yourticketapp.Cupones.CuponAdapter
import kotlinx.android.synthetic.main.fragment_cupones_guardados.view.*

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

        val adRequest = AdRequest.Builder().build()
        root.adViewCuponesGuardados.loadAd(adRequest)

        return root
    }

    fun cargarLista(root: View): View {
        val listaCuponesID = arrayListOf<String>()
        val db = FirebaseFirestore.getInstance()
        db.collection("cuponGuardado")
            .whereEqualTo("id_usuario", PrincipalActivity.globalVarId)
            .get()
            .addOnSuccessListener { documents ->
                for (documento in documents) {
                    listaCuponesID.add(documento["id_cupon"].toString())
                }
                db.collection("cupon")
                    .get()
                    .addOnSuccessListener { documents ->
                        for (documento in documents) {
                            for (cuponID in listaCuponesID){
                                if(documento.id == cuponID){
                                    val cupon = documento.toObject(Cupon::class.java)
                                    cupon.id = documento.id
                                    listaCupones.add(cupon)
                                }
                            }
                        }
                        cuponAdapter = CuponAdapter(this, listaCupones, R.layout.row_cupon)
                        recyclerViewCupon = root.findViewById(R.id.recyclerViewCuponesGuardados)
                        recyclerViewCupon.layoutManager = LinearLayoutManager(context)
                        recyclerViewCupon.adapter = cuponAdapter
                    }
            }
        return root
    }
}