package com.lacuc.pets.ui.manage.group.info

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jakewharton.rxbinding4.InitialValueObservable
import com.lacuc.pets.data.Result
import com.lacuc.pets.domain.member.GetGroupMemberUseCase
import com.lacuc.pets.domain.member.UpdateGroupMemberUseCase
import com.lacuc.pets.util.SingleLiveEvent
import com.lacuc.pets.util.safeValue
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class UserProfileViewModel @Inject constructor(
    private val updateProfileUseCase: UpdateGroupMemberUseCase,
    private val getGroupMemberUseCase: GetGroupMemberUseCase,
    private val errorEvent: SingleLiveEvent<String>
) : ViewModel() {

    var gid = ""
    var uid = ""

    val name = MutableLiveData("")
    val email = MutableLiveData("")

    val completeBtnEnable = MutableLiveData(false)

    val completeEvent = SingleLiveEvent<Unit>()

    fun updateProfile() {
        viewModelScope.launch {
            val result = updateProfileUseCase(gid, uid, name.safeValue, email.safeValue)

            when (result) {
                is Result.Success -> completeEvent.value = Unit
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

    fun bindCompleteBtnEnable(
        nameObservable: InitialValueObservable<CharSequence>,
        emailObservable: InitialValueObservable<CharSequence>
    ): Disposable =
        Observable.combineLatest(nameObservable, emailObservable) { nameText, emailText ->
            nameText.isNotEmpty() && emailText.isNotEmpty()
        }.subscribe(completeBtnEnable::setValue)

    fun loadProfile() {
        viewModelScope.launch {
            val profile = getGroupMemberUseCase(gid, uid)

            when (profile) {
                is Result.Success -> profile.body?.let {
                    name.value = it.name
                    email.value = it.email
                }
                is Result.Failure -> errorEvent.value =
                    "code: ${profile.code} message: ${profile.error}"
                is Result.NetworkError -> errorEvent.value = "네트워크 문제가 발생했습니다."
                is Result.Unexpected -> {
                    Timber.d(profile.t.toString())
                    errorEvent.value = "알수없는 오류가 발생했습니다."
                }
            }
        }
    }
}