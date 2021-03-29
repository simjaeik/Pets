package com.lacuc.pets

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.lacuc.pets.LiveDataTestUtil.getOrAwaitValue
import com.lacuc.pets.data.LoginService
import com.lacuc.pets.domain.SignUpUseCase
import com.lacuc.pets.ui.SignUpViewModel
import io.reactivex.rxjava3.core.Observable
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock

class SignUpViewModelTest {

    private lateinit var viewModel: SignUpViewModel

    @Mock
    private lateinit var loginService: LoginService

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun init_viewModel() {
        loginService = mock(LoginService::class.java)
        viewModel = SignUpViewModel(SignUpUseCase(loginService))
    }

    @Test
    fun passwordEqualCheck_samePassword() {
        viewModel.completeBtnEnable.value = false

        viewModel.setCompleteBtnEnableChecker(
            Observable.just("password"),
            Observable.just("password")
        )

        assertTrue(viewModel.completeBtnEnable.getOrAwaitValue())
    }
}