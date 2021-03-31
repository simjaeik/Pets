package com.lacuc.pets.domain

import com.lacuc.pets.data.LoginService

class SignInUseCase(private val loginService: LoginService) {
    operator fun invoke(email: String, password: String): Boolean {
        TODO()
    }
}