package com.lacuc.pets.data.animal.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Animal(
    val aid: Int,
    val gid: Int,
    val name: String,
    val image: String,
    val age: Int,
    val sex: String,
    val species: String,
    val subspecies: String,
    val weight: Double,
    val number: String,
) : Parcelable