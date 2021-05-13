package com.lacuc.pets.domain.login

import com.lacuc.pets.data.Result
import com.lacuc.pets.data.SimpleBoolResult
import com.lacuc.pets.data.login.LoginService
import javax.inject.Inject

class NickNameCheckUseCase @Inject constructor(private val loginService: LoginService) {
    suspend operator fun invoke(nickName: String): Result<SimpleBoolResult> {
        return loginService.isNickNameExist(nickName)
    }
}