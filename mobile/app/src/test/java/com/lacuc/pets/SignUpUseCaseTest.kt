package com.lacuc.pets

import com.lacuc.pets.data.LoginService
import com.lacuc.pets.domain.SignUpUseCase
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
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

    @Test
    fun signUp_newEmail() {
        val param = mapOf(
            "name" to "John",
            "email" to "newUser@lacuc.com",
            "password" to "password"
        )
        `when`(loginService.signUp(param)).thenReturn(true)

        val result = useCase("John", "newUser@lacuc.com", "password")

        verify(loginService).signUp(param)
        assertTrue(result)
    }

}