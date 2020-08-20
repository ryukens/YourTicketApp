package com.moralesjuan.yourticketapp.categorias

data class Category (var nombre_cat:String, var path_imagen:String){
    constructor():this("Default name","default path")
}