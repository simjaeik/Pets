package com.lacuc.pets.data.group.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Group(
    val gid: Int,
    val name: String,
    val info: String,
    val image: String,
    val share: Boolean,
    val latitude: Double = 0.0,
    val longitude: Double = 0.0
) : Parcelable