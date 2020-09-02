package com.moralesjuan.yourticketapp.Cupones

data class Cupon(var promocion: String, var path_cupon: String) {
    constructor() : this("", "")
}