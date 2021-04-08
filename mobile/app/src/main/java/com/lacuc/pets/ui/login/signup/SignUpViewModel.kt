package com.lacuc.pets.ui.login.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lacuc.pets.domain.login.SignUpUseCase
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableSource
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject

class SignUpViewModel @Inject constructor(private val signUpUseCase: SignUpUseCase) : ViewModel() {

    val completeBtnEnable = MutableLiveData(false)
    val passwordConfirmError = MutableLiveData<String?>()

    fun bindPasswordConfirmError(
        password: ObservableSource<CharSequence>,
        passwordConfirm: ObservableSource<CharSequence>
    ): Disposable = Observable.combineLatest(password, passwordConfirm, { p, pc ->
        p.toString() == pc.toString()
    }).subscribe { isMatch ->
        if (isMatch) {
            passwordConfirmError.value = null
        } else {
            passwordConfirmError.value = "패스워드가 일치하지 않습니다"
        }
    }

    fun bindCompleteBtnEnable(
        name: ObservableSource<CharSequence>,
        email: ObservableSource<CharSequence>,
        password: ObservableSource<CharSequence>,
        passwordConfirm: ObservableSource<CharSequence>
    ): Disposable =
        Observable.combineLatest(name, email, password, passwordConfirm, { n, e, p, pc ->
            n.isNotEmpty() && e.isNotEmpty() && p.isNotEmpty() && pc.isNotEmpty() && p.toString() == pc.toString()
        }).subscribe {
            completeBtnEnable.value = it
        }
}