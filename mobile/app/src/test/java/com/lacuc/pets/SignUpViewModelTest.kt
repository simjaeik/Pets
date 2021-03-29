package com.lacuc.pets

import com.lacuc.pets.data.LoginService
import com.lacuc.pets.domain.SignUpUseCase
import com.lacuc.pets.ui.SignUpViewModel
import org.junit.Before
import org.mockito.Mock

class SignUpViewModelTest {

    lateinit var viewModel: SignUpViewModel

    @Mock
    lateinit var loginService: LoginService

    @Before
    fun init_viewModel() {
        viewModel = SignUpViewModel(SignUpUseCase(loginService))
    }
}