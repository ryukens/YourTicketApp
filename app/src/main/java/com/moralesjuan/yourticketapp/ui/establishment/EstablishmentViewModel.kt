package com.moralesjuan.yourticketapp.ui.establishment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EstablishmentViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is establishment Fragment"
    }
    val text: LiveData<String> = _text
}