package com.lacuc.pets.ui.login.signin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lacuc.pets.data.Result
import com.lacuc.pets.domain.login.SignInUseCase
import com.lacuc.pets.util.SingleLiveEvent
import com.lacuc.pets.util.TokenManager
import com.lacuc.pets.util.safeValue
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val tokenManager: TokenManager,
    private val errorEvent: SingleLiveEvent<String>
) : ViewModel() {

    val email = MutableLiveData("")
    val password = MutableLiveData("")

    val completeEvent = SingleLiveEvent<Unit>()

    fun signIn() {
        viewModelScope.launch {
            val result = signInUseCase(email.safeValue, password.safeValue)

            when (result) {
                is Result.Success -> {
                    result.body?.let {
                        tokenManager.saveToken(it.token)
                        completeEvent.value = Unit
                    }
                }
                is Result.Failure -> errorEvent.value =
                    "code: ${result.code} message: ${result.error}"
                is Result.NetworkError -> errorEvent.value = "네트워크 문제가 발생했습니다."
                is Result.Unexpected -> {
                    Timber.d(result.t.toString())
                    errorEvent.value = "알수없는 오류가 발생했습니다."
                }
            }
        }
    }
}