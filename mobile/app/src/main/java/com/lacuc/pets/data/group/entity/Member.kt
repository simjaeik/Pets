package com.lacuc.pets.data.group.entity

data class Member(
    val UID: String,
    val name: String,
    val password: String,
    val email: String,
    val nickname: String,
    val authority: String = "권한없음"
)