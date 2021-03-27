package com.lacuc.pets

import com.lacuc.pets.data.LoginService
import com.lacuc.pets.domain.SignUpUseCase
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SignUpUseCaseTest {

    @Mock
    lateinit var loginService: LoginService

    lateinit var useCase: SignUpUseCase

    @Before
    fun init_UseCase_With_MockService() {
        loginService = mock(LoginService::class.java)
        useCase = SignUpUseCase(loginService)
    }

}