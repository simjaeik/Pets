package com.lacuc.pets.ui.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.lacuc.pets.data.login.LoginService
import com.lacuc.pets.domain.login.SignUpUseCase
import com.lacuc.pets.ui.login.signup.SignUpViewModel
import com.lacuc.pets.util.LiveDataTestUtil.getOrAwaitValue
import io.reactivex.rxjava3.core.Observable
import org.junit.Assert.*
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
    fun passwordConfirmCheck_samePassword() {
        viewModel.bindPasswordConfirmError(
            Observable.just("password"),
            Observable.just("password")
        )

        assertNull(viewModel.passwordConfirmError.getOrAwaitValue())
    }

    @Test
    fun passwordEqualCheck_differentPassword() {
        viewModel.bindPasswordConfirmError(
            Observable.just("password"),
            Observable.just("otherPassword")
        )

        assertEquals(viewModel.passwordConfirmError.getOrAwaitValue(),
            "패스워드가 일치하지 않습니다")
    }

    @Test
    fun completeBtnEnable_emptyEmail() {
        viewModel.bindCompleteBtnEnable(
            Observable.just("name"),
            Observable.just(""),
            Observable.just("password"),
            Observable.just("password")
        )

        assertFalse(viewModel.completeBtnEnable.getOrAwaitValue())
    }

    @Test
    fun completeBtnEnable_emptyName() {
        viewModel.bindCompleteBtnEnable(
            Observable.just(""),
            Observable.just("email@lacuc.com"),
            Observable.just("password"),
            Observable.just("password")
        )

        assertFalse(viewModel.completeBtnEnable.getOrAwaitValue())
    }

    @Test
    fun completeBtnEnable_emptyPassword() {
        viewModel.bindCompleteBtnEnable(
            Observable.just("name"),
            Observable.just("email@lacuc.com"),
            Observable.just(""),
            Observable.just("password")
        )

        assertFalse(viewModel.completeBtnEnable.getOrAwaitValue())
    }

    @Test
    fun completeBtnEnable_emptyPasswordConfirm() {
        viewModel.bindCompleteBtnEnable(
            Observable.just("name"),
            Observable.just("email@lacuc.com"),
            Observable.just("password"),
            Observable.just("")
        )

        assertFalse(viewModel.completeBtnEnable.getOrAwaitValue())
    }

    @Test
    fun completeBtnEnable_differentPassword() {
        viewModel.bindCompleteBtnEnable(
            Observable.just("name"),
            Observable.just("email@lacuc.com"),
            Observable.just("password"),
            Observable.just("otherPassword")
        )

        assertFalse(viewModel.completeBtnEnable.getOrAwaitValue())
    }

    @Test
    fun completeBtnEnable_validInput() {
        viewModel.bindCompleteBtnEnable(
            Observable.just("name"),
            Observable.just("email@lacuc.com"),
            Observable.just("password"),
            Observable.just("password")
        )

        assertTrue(viewModel.completeBtnEnable.getOrAwaitValue())
    }

}