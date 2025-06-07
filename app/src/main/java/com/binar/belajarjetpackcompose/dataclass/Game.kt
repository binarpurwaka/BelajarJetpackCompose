package com.binar.belajarjetpackcompose.dataclass

data class Game(
    val id: Int,
    val judul: String?,
    val tahun: String?,
    val image: String?,
    val label: String?,
    val genre: String?,
    val link: String?,
    val dev: Developer?
)

data class Developer(
    val satu: String?,
    val dua: String?,
    val tiga: String?
)