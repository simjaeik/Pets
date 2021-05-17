package com.lacuc.pets.data.animal.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Animal(
    val AID: String,
    val GID: String,
    val name: String,
    val image: String,
    val age: String,
    val sex: String,
    val species: String,
    val subspecies: String,
    val weight: String,
    val number: String,
) : Parcelable