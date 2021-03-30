package com.lacuc.pets

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.lacuc.pets.LiveDataTestUtil.getOrAwaitValue
import com.lacuc.pets.data.LoginService
import com.lacuc.pets.domain.SignUpUseCase
import com.lacuc.pets.ui.SignUpViewModel
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
        viewModel.setPasswordConfirmHelperTextEnableWatcher(
            Observable.just("password"),
            Observable.just("password")
        )

        assertNull(viewModel.passwordConfirmError.getOrAwaitValue())
    }

    @Test
    fun passwordEqualCheck_differentPassword() {
        viewModel.setPasswordConfirmHelperTextEnableWatcher(
            Observable.just("password"),
            Observable.just("otherPassword")
        )

        assertEquals(viewModel.passwordConfirmError.getOrAwaitValue(),
            "패스워드가 일치하지 않습니다")
    }

    @Test
    fun completeBtnEnable_emptyEmail() {
        viewModel.setCompleteBtnEnableWatcher(
            Observable.just("name"),
            Observable.just(""),
            Observable.just("password"),
            Observable.just("password")
        )

        assertFalse(viewModel.completeBtnEnable.getOrAwaitValue())
    }

    @Test
    fun completeBtnEnable_emptyName() {
        viewModel.setCompleteBtnEnableWatcher(
            Observable.just(""),
            Observable.just("email@lacuc.com"),
            Observable.just("password"),
            Observable.just("password")
        )

        assertFalse(viewModel.completeBtnEnable.getOrAwaitValue())
    }

    @Test
    fun completeBtnEnable_emptyPassword() {
        viewModel.setCompleteBtnEnableWatcher(
            Observable.just("name"),
            Observable.just("email@lacuc.com"),
            Observable.just(""),
            Observable.just("password")
        )

        assertFalse(viewModel.completeBtnEnable.getOrAwaitValue())
    }

    @Test
    fun completeBtnEnable_emptyPasswordConfirm() {
        viewModel.setCompleteBtnEnableWatcher(
            Observable.just("name"),
            Observable.just("email@lacuc.com"),
            Observable.just("password"),
            Observable.just("")
        )

        assertFalse(viewModel.completeBtnEnable.getOrAwaitValue())
    }

    @Test
    fun completeBtnEnable_differentPassword() {
        viewModel.setCompleteBtnEnableWatcher(
            Observable.just("name"),
            Observable.just("email@lacuc.com"),
            Observable.just("password"),
            Observable.just("otherPassword")
        )

        assertFalse(viewModel.completeBtnEnable.getOrAwaitValue())
    }

    @Test
    fun completeBtnEnable_validInput() {
        viewModel.setCompleteBtnEnableWatcher(
            Observable.just("name"),
            Observable.just("email@lacuc.com"),
            Observable.just("password"),
            Observable.just("password")
        )

        assertTrue(viewModel.completeBtnEnable.getOrAwaitValue())
    }

}