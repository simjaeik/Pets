package com.lacuc.pets.data.group.entity

import com.google.gson.annotations.SerializedName

data class Member(
    val UID: String,
    @SerializedName("Member.name") val name: String,
    val password: String,
    @SerializedName("Member.email") val email: String,
    @SerializedName("Member.nickName") val nickname: String,
    val authority: String = "권한없음"
)