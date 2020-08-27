package com.moralesjuan.yourticketapp.Categoria

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.moralesjuan.yourticketapp.R


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
        //cargarEstablecimientos(root)

//        val ImageView_Cat_Comida: ImageView = root.findViewById(R.id.imageView_Cat_Comida)
//        val ImageView_Cat_Ropa: ImageView = root.findViewById(R.id.imageView_Cat_Ropa)
//        val ImageView_Cat_Articulos: ImageView = root.findViewById(R.id.imageView_Cat_Articulos)
//        val ImageView_Cat_Cine: ImageView = root.findViewById(R.id.imageView_Cat_Cine)
//        val ImageView_Cat_Musica: ImageView = root.findViewById(R.id.imageView_Cat_Musica)
//        val nuevoFragmento = EstablecimientosFragment()
//        ImageView_Cat_Comida.setOnClickListener(){
//            val transaction = requireFragmentManager().beginTransaction()
//            transaction.add(R.id.fragment_categorias_xml, nuevoFragmento)
//            transaction.addToBackStack(null)
//            transaction.commit()
//        }
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
                Toast.makeText(
                    context,
                    "Base de datos leida",
                    Toast.LENGTH_SHORT
                ).show()

                categoriaAdapter =
                    CategoriaAdapter(
                        this,
                        listaCategorias,
                        R.layout.row_categoria
                    )
                recyclerViewCategoria = root.findViewById(R.id.recyclerViewCategoria)
                recyclerViewCategoria.layoutManager = LinearLayoutManager(context)
                recyclerViewCategoria.adapter = categoriaAdapter

            }
        return root
    }
}