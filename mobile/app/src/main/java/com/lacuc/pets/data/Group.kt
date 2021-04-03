package com.lacuc.pets.data

data class Group(
    val name: String,
    val info: String,
    val image: String,
    val share: Boolean,
    val latitude: Double = 0.0,
    val longitude: Double = 0.0
)
