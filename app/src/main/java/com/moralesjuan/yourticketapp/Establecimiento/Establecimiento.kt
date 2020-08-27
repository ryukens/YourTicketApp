package com.moralesjuan.yourticketapp.Establecimiento

data class Establecimiento(
    var categoria_est: String,
    var nombre_est: String,
    var path_est: String
) {
    constructor() : this("", "", "")
}