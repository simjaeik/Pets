package com.lacuc.pets.ui.manage.group.member.invite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lacuc.pets.data.Result
import com.lacuc.pets.domain.member.InviteMemberUseCase
import com.lacuc.pets.util.SingleLiveEvent
import com.lacuc.pets.util.safeValue
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class InviteMemberViewModel @Inject constructor(
    private val inviteMemberUseCase: InviteMemberUseCase,
    private val errorEvent: SingleLiveEvent<String>
) : ViewModel() {

    var gid = ""

    val email = MutableLiveData("")

    val completeEvent = SingleLiveEvent<Unit>()

    fun inviteMember() {
        viewModelScope.launch {
            val result = inviteMemberUseCase(gid, email.safeValue)

            when (result) {
                is Result.Success -> result.body?.let { completeEvent.value = Unit }
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