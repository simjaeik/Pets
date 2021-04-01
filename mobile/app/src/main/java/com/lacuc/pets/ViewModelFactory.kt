package com.lacuc.pets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lacuc.pets.data.LoginService
import com.lacuc.pets.domain.login.SignUpUseCase
import com.lacuc.pets.ui.login.signup.SignUpViewModel

class ViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(SignUpViewModel::class.java) ->
                    SignUpViewModel(
                        SignUpUseCase(object : LoginService {})
                    )
                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
}
