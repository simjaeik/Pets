package com.lacuc.pets

import com.lacuc.pets.data.LoginService
import com.lacuc.pets.domain.SignInUseCase
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
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

    @Test
    fun signIn_existentUser() {
        val param = mapOf(
            "email" to "oldUser@lacuc.com",
            "password" to "password"
        )
        Mockito.`when`(loginService.signIn(param)).thenReturn(true)

        val result = useCase("oldUser@lacuc.com", "password")

        Assert.assertTrue(result)
        Mockito.verify(loginService).signIn(Mockito.anyMap())
    }

    @Test
    fun signIn_nonexistentUser() {
        Mockito.`when`(loginService.signIn(ArgumentMatchers.anyMap())).thenAnswer {
            val email = it.getArgument<Map<String, String>>(0)["email"]
            email in listOf("oldUser1@lacuc.com", "oldUser2@lacuc.com")
        }

        Assert.assertFalse(useCase("newUser1@lacuc.com", "password"))
        Assert.assertFalse(useCase("newUser2@lacuc.com", "password"))
        Assert.assertTrue(useCase("oldUser1@lacuc.com", "password"))
        Mockito.verify(loginService, Mockito.times(3)).signIn(Mockito.anyMap())
    }

}