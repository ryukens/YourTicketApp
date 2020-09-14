package com.moralesjuan.yourticketapp.Establecimiento

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.moralesjuan.yourticketapp.R
import kotlinx.android.synthetic.main.fragment_categorias.view.*

class EstablecimientosFragment(private var nombre_categoria: String) : Fragment() {

    private lateinit var establecimientoAdapter: EstablecimientoAdapter
    private lateinit var recyclerViewCategoria: RecyclerView
    private var listaEstablecimientos = arrayListOf<Establecimiento>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var root = inflater.inflate(R.layout.fragment_categorias, container, false)

        val textViewTitulo:TextView = root.findViewById(R.id.textViewCatego)
        textViewTitulo.text = nombre_categoria
        root = cargarLista(root, nombre_categoria)

        val adRequest = AdRequest.Builder().build()
        root.adViewCategory.loadAd(adRequest)

        return root
    }

    fun cargarLista(root: View, nombre_categoria: String): View {
        val db = FirebaseFirestore.getInstance()
        db.collection("establecimiento")
            .whereEqualTo("categoria_est", nombre_categoria)
            .get()
            .addOnSuccessListener { documents ->
                for (documento in documents) {
                    val establecimiento = documento.toObject(Establecimiento::class.java)
                    listaEstablecimientos.add(establecimiento)
                }
//                Toast.makeText(context, "Base de datos leida", Toast.LENGTH_SHORT).show()

                establecimientoAdapter =
                    EstablecimientoAdapter(this, listaEstablecimientos, R.layout.row_categoria)
                recyclerViewCategoria = root.findViewById(R.id.recyclerViewCategoria)
                recyclerViewCategoria.layoutManager = LinearLayoutManager(context)
                recyclerViewCategoria.adapter = establecimientoAdapter
            }
        return root
    }
}