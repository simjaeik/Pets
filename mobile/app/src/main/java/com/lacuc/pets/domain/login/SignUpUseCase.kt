package com.lacuc.pets.domain.login

import com.lacuc.pets.data.Result
import com.lacuc.pets.data.SimpleBoolResult
import com.lacuc.pets.data.login.LoginService
import javax.inject.Inject

class SignUpUseCase @Inject constructor(private val loginService: LoginService) {
    suspend operator fun invoke(
        name: String, password: String, email: String, nickName: String
    ): Result<SimpleBoolResult> {
        return loginService.signUp(
            mapOf(
                "name" to name, "password" to password, "email" to email, "nickName" to nickName
            )
        )
    }
}