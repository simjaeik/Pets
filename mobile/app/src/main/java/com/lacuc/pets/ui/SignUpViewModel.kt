package com.lacuc.pets.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lacuc.pets.domain.SignUpUseCase
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableSource
import io.reactivex.rxjava3.disposables.Disposable

class SignUpViewModel(private val signUpUseCase: SignUpUseCase) : ViewModel() {

    val completeBtnEnable = MutableLiveData(false)
    val passwordConfirmHelperTextEnable = MutableLiveData(false)

    fun setPasswordConfirmHelperTextEnableWatcher(
        password: ObservableSource<String>,
        passwordConfirm: ObservableSource<String>
    ): Disposable = Observable.combineLatest(password, passwordConfirm, { p, pc ->
        p == pc
    }).subscribe {
        passwordConfirmHelperTextEnable.value = it
    }

    fun setCompleteBtnEnableWatcher(
        name: ObservableSource<String>,
        email: ObservableSource<String>,
        password: ObservableSource<String>,
        passwordConfirm: ObservableSource<String>
    ): Disposable =
        Observable.combineLatest(name, email, password, passwordConfirm, { name, email, p, pc ->
            true
        }).subscribe {
            completeBtnEnable.value = it
        }
}