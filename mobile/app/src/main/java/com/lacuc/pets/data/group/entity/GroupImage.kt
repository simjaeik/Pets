package com.lacuc.pets.data.group.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GroupImage(
    val IID: String,
    val url: String,
    val tag: String
) : Parcelable