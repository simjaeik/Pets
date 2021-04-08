package com.lacuc.pets.domain.login

import com.lacuc.pets.data.LoginService

class SignInUseCase(private val loginService: LoginService) {
    operator fun invoke(email: String, password: String): Boolean {
        return loginService.signIn(mapOf("email" to email, "password" to password))
    }
}