package com.lacuc.pets

import com.lacuc.pets.data.LoginService
import com.lacuc.pets.domain.SignInUseCase
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SignInUseCaseTest {

    @Mock
    lateinit var loginService: LoginService

    lateinit var useCase: SignInUseCase

    @Before
    fun init_UseCase_With_MockService() {
        loginService = Mockito.mock(LoginService::class.java)
        useCase = SignInUseCase(loginService)
    }

}