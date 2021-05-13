package com.lacuc.pets.data.group.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GroupImage(
    val iid: String,
    val gid: String,
    val url: String,
    val tag: String
) : Parcelable