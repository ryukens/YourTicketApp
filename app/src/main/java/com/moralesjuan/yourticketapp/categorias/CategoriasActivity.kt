package com.moralesjuan.yourticketapp.categorias

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.moralesjuan.yourticketapp.R
import kotlinx.android.synthetic.main.activity_categorias.*

class CategoriasActivity : AppCompatActivity() {

    private lateinit var adapter: MainAdapterCategory
    private val viewModel by lazy { ViewModelProviders.of(this).get(MainViewModelCategory::class.java)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categorias)

        adapter = MainAdapterCategory(this)
        recyclerViewCategorias.layoutManager = LinearLayoutManager(this)
        recyclerViewCategorias.adapter = adapter
        observeData()

/*
        val dummyList = mutableListOf<Category>()
        dummyList.add(Category("Food","https://static.independent.co.uk/s3fs-public/thumbnails/image/2016/12/29/16/junk-food-istock-zeljkosantrac.jpg?w968h681"))
        dummyList.add(Category("Cloth","https://media.informabtl.com/wp-content/uploads/2017/10/marcas-de-ropa-ma%CC%81s-valiosas-2017-retail.jpg"))
        dummyList.add(Category("Car","https://kayum.mx/wp-content/uploads/elementor/thumbs/Autos-populares-Uber-ohjsq47a5kbjrr6nxy00mff8o842m4etftfxi4ixeo.jpg"))

         adapter.setListData(dummyList)
        adapter.notifyDataSetChanged()

*/

    }
    fun observeData(){
        viewModel.fetchUserData().observe(this, Observer {
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}