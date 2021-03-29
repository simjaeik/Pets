package com.lacuc.pets.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lacuc.pets.domain.SignUpUseCase
import io.reactivex.rxjava3.core.ObservableSource

class SignUpViewModel(private val signUpUseCase: SignUpUseCase) : ViewModel() {

    val completeBtnEnable = MutableLiveData<Boolean>()

    fun isPasswordEqual(
        password: ObservableSource<String>,
        passwordRepeat: ObservableSource<String>
    ) {

    }
}