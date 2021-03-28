package com.lacuc.pets.domain

import com.lacuc.pets.data.LoginService

class SignUpUseCase(val loginService: LoginService) {
    operator fun invoke(name: String, email: String, password: String): Boolean {
        TODO("Not yet implemented")
    }
}