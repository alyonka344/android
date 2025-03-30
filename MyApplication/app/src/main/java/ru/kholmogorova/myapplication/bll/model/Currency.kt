package ru.kholmogorova.myapplication.bll.model

data class Currency(
    val base: String,
    val date: String,
    val rates: Map<String, Double>
)