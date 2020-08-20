package com.moralesjuan.yourticketapp.categorias

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore


class Repo {
    fun getCategoryData(): LiveData<MutableList<Category>> {
        //:LiveData<MutableList<User>>
        val mutableData = MutableLiveData<MutableList<Category>>()
        FirebaseFirestore.getInstance().collection("categoria").get().addOnSuccessListener { result ->
            val listData : MutableList<Category> = mutableListOf<Category>()
            for(document in result){
                val nombre_cat = document.getString("nombre_cat")
                val path_imagen = document.getString("path_imagen")
                val user = Category(
                    nombre_cat!!.toString(),
                    path_imagen!!.toString()
                )
                listData.add(user)
            }
            mutableData.value = listData
        }
        return mutableData
    }


}