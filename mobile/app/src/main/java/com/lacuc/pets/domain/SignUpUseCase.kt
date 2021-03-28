package com.lacuc.pets.domain

import com.lacuc.pets.data.LoginService

class SignUpUseCase(private val loginService: LoginService) {
    operator fun invoke(name: String, email: String, password: String): Boolean {
        return loginService.signUp(mapOf("name" to name, "email" to email, "password" to password))
    }
}