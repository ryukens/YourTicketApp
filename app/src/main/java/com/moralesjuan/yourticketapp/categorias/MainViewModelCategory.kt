package com.moralesjuan.yourticketapp.categorias

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModelCategory: ViewModel() {
    private val repo = Repo()
    fun fetchUserData(): LiveData<MutableList<Category>> {
        val mutableData = MutableLiveData<MutableList<Category>>()
        repo.getCategoryData().observeForever { categoryList ->
            mutableData.value = categoryList
        }
        return mutableData
    }

}