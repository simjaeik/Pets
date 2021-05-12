package com.lacuc.pets.ui.login.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jakewharton.rxbinding4.InitialValueObservable
import com.lacuc.pets.R
import com.lacuc.pets.data.Result
import com.lacuc.pets.domain.login.EmailCheckUseCase
import com.lacuc.pets.domain.login.NickNameCheckUseCase
import com.lacuc.pets.domain.login.SignUpUseCase
import com.lacuc.pets.util.SingleLiveEvent
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableSource
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val emailCheckUseCase: EmailCheckUseCase,
    private val nickNameCheckUseCase: NickNameCheckUseCase,
    private val errorEvent: SingleLiveEvent<String>
) : ViewModel() {

    val name = MutableLiveData("")
    val password = MutableLiveData("")
    val passwordConfirm = MutableLiveData("")
    val email = MutableLiveData("")
    val nickName = MutableLiveData("")

    val completeBtnEnable = MutableLiveData(false)

    val passwordConfirmError = MutableLiveData<String?>()

    val isEmailDuplicated = MutableLiveData<String?>(null)

    val emailEndIcon = MutableLiveData<Int?>()

    val isNickNameDuplicated = MutableLiveData<String?>(null)

    val nickNameEndIcon = MutableLiveData<Int?>()

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

    fun bindEmailDuplicate(emailChanges: InitialValueObservable<CharSequence>): Disposable =
        emailChanges.debounce(500, TimeUnit.MILLISECONDS)
            .subscribe(this::checkEmailDuplication)

    fun bindNickNameDuplicate(nickNameChanges: InitialValueObservable<CharSequence>): Disposable =
        nickNameChanges.debounce(500, TimeUnit.MILLISECONDS)
            .subscribe(this::checkNickNameDuplication)

    private fun checkEmailDuplication(email: CharSequence) {
        Timber.d("이메일 중복 확인")
        if (email.isEmpty()) {
            isEmailDuplicated.postValue(null)
            emailEndIcon.postValue(null)
            return
        }

        viewModelScope.launch {
            val result = emailCheckUseCase(email.toString())

            when (result) {
                is Result.Success -> result.body?.let {
                    if (it.result) {
                        isEmailDuplicated.value = "이미 존재하는 이메일입니다."
                        emailEndIcon.value = null
                    } else {
                        isEmailDuplicated.value = null
                        emailEndIcon.value = R.drawable.ic_baseline_check_24
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

    private fun checkNickNameDuplication(nickName: CharSequence) {
        Timber.d("이메일 중복 확인")
        if (nickName.isEmpty()) {
            isNickNameDuplicated.postValue(null)
            nickNameEndIcon.postValue(null)
            return
        }

        viewModelScope.launch {
            val result = nickNameCheckUseCase(nickName.toString())

            when (result) {
                is Result.Success -> result.body?.let {
                    if (it.result) {
                        isNickNameDuplicated.value = "이미 존재하는 닉네임입니다."
                        nickNameEndIcon.value = null
                    } else {
                        isNickNameDuplicated.value = null
                        nickNameEndIcon.value = R.drawable.ic_baseline_check_24
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