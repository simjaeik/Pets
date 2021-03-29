package com.lacuc.pets.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lacuc.pets.domain.SignUpUseCase
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableSource
import io.reactivex.rxjava3.disposables.Disposable

class SignUpViewModel(private val signUpUseCase: SignUpUseCase) : ViewModel() {

    val completeBtnEnable = MutableLiveData<Boolean>()

    fun setCompleteBtnEnableChecker(
        password: ObservableSource<String>,
        passwordRepeat: ObservableSource<String>
    ): Disposable = Observable.combineLatest(password, passwordRepeat, { p, pr ->
        p == pr
    }).subscribe {
        completeBtnEnable.value = it
    }
}