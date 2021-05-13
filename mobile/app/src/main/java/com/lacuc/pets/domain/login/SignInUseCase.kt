package com.lacuc.pets.domain.login

import com.lacuc.pets.data.Result
import com.lacuc.pets.data.TokenResponse
import com.lacuc.pets.data.login.LoginService
import javax.inject.Inject

class SignInUseCase @Inject constructor(private val loginService: LoginService) {
    suspend operator fun invoke(email: String, password: String): Result<TokenResponse> {
        return loginService.signIn(email, password)
    }
}