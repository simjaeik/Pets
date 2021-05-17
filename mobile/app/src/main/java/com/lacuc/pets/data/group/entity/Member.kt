package com.lacuc.pets.data.group.entity

data class Member(
    val UID: String,
    val name: String,
    val password: String,
    val email: String,
    val nickName: String,
    val authority: String = "권한없음"
)