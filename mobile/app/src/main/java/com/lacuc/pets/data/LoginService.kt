package com.lacuc.pets.data

interface LoginService {
    fun signUp(param: Map<String, String>): Boolean

    fun signIn(param: Map<String, String>): Boolean
}