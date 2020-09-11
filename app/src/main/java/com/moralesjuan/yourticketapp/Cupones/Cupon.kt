package com.moralesjuan.yourticketapp.Cupones

data class Cupon(var id:String ,var promocion: String, var path_cupon: String) {
    constructor() : this("", "", "")
}