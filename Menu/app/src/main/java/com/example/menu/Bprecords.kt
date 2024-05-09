package com.example.menu

data class Bprecords (
    val bprecords: List<Bprecord>
    )

data class Bprecord(
    val datetime: String,
    val sys: Int,
    val dia: Int,
    val hr: Int
)