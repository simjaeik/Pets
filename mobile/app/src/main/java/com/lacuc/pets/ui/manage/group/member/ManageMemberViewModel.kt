package com.lacuc.pets.ui.manage.group.member

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lacuc.pets.data.Result
import com.lacuc.pets.data.group.entity.Member
import com.lacuc.pets.domain.member.GetGroupMembersUseCase
import com.lacuc.pets.util.SingleLiveEvent
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class ManageMemberViewModel @Inject constructor(
    private val getGroupMembersUseCase: GetGroupMembersUseCase,
    private val errorEvent: SingleLiveEvent<String>
) : ViewModel() {

    val members = MutableLiveData<List<Member>>()

    fun loadMembers(gid: Int) {
        viewModelScope.launch {
            val memberList = getGroupMembersUseCase(gid)

            when (memberList) {
                is Result.Success -> memberList.body?.let { members.value = it }
                is Result.Failure -> errorEvent.value =
                    "code: ${memberList.code} message: ${memberList.error}"
                is Result.NetworkError -> errorEvent.value = "네트워크 문제가 발생했습니다."
                is Result.Unexpected -> {
                    Timber.d(memberList.t.toString())
                    errorEvent.value = "알수없는 오류가 발생했습니다."
                }
            }
        }
    }
}