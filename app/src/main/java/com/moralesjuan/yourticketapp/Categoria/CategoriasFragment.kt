package com.moralesjuan.yourticketapp.Categoria

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.moralesjuan.yourticketapp.R
import kotlinx.android.synthetic.main.fragment_categorias.view.*


class CategoriasFragment : Fragment() {

    private lateinit var categoriaAdapter: CategoriaAdapter
    private lateinit var recyclerViewCategoria: RecyclerView
    private var listaCategorias = arrayListOf<Categoria>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var root = inflater.inflate(R.layout.fragment_categorias, container, false)

        root = cargarLista(root)

        val adRequest = AdRequest.Builder().build()
        root.adViewCategory.loadAd(adRequest)

        return root
    }

    fun cargarLista(root: View): View {
        val db = FirebaseFirestore.getInstance()
        db.collection("categoria")
            .get()
            .addOnSuccessListener { documents ->
                for (documento in documents) {
                    val categoria = documento.toObject(Categoria::class.java)
                    listaCategorias.add(categoria)
                }

                categoriaAdapter = CategoriaAdapter(this, listaCategorias, R.layout.row_categoria)
                recyclerViewCategoria = root.findViewById(R.id.recyclerViewCategoria)
                recyclerViewCategoria.layoutManager = LinearLayoutManager(context)
                recyclerViewCategoria.adapter = categoriaAdapter

            }
        return root
    }
}