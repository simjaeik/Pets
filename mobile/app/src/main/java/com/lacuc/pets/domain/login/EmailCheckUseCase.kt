package com.lacuc.pets.domain.login

import com.lacuc.pets.data.Result
import com.lacuc.pets.data.SimpleBoolResult
import com.lacuc.pets.data.login.LoginService
import javax.inject.Inject

class EmailCheckUseCase @Inject constructor(private val loginService: LoginService) {
    suspend operator fun invoke(email: String): Result<SimpleBoolResult> {
        return loginService.isEmailExist(email)
    }
}