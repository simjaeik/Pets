package com.lacuc.pets.domain.login

import com.lacuc.pets.data.login.LoginService
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
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

    @Test
    fun signUp_duplicatedEmail() {
        `when`(loginService.signUp(ArgumentMatchers.anyMap())).thenAnswer {
            it.getArgument<Map<String, String>>(0)["name"] !in listOf("Alice", "Bob")
        }

        assertFalse(useCase("Alice", "existingUser1@lacuc.com", "password"))
        assertFalse(useCase("Bob", "existingUser2@lacuc.com", "password"))
        assertTrue(useCase("John", "newUser@lacuc.com", "password"))
        verify(loginService, times(3)).signUp(anyMap())
    }

}