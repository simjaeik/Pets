package com.lacuc.pets.domain.login

import com.lacuc.pets.data.LoginService
import javax.inject.Inject

class SignUpUseCase @Inject constructor(private val loginService: LoginService) {
    operator fun invoke(name: String, email: String, password: String) {
        return loginService.signUp(mapOf("name" to name, "email" to email, "password" to password))
    }
}