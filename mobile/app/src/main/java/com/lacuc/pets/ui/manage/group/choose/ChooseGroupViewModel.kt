package com.lacuc.pets.ui.manage.group.choose

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lacuc.pets.data.Result
import com.lacuc.pets.domain.group.GetGroupUseCase
import com.lacuc.pets.domain.group.GroupItem
import com.lacuc.pets.util.SingleLiveEvent
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class ChooseGroupViewModel @Inject constructor(
    private val getGroupUseCase: GetGroupUseCase,
    private val errorEvent: SingleLiveEvent<String>
) :
    ViewModel() {

    val groupItems = MutableLiveData<List<GroupItem>>()

    val loading = MutableLiveData(false)

    val groupClickEvent = SingleLiveEvent<GroupItem>()

    init {
        loadGroups()
    }

    fun loadGroups() {
        viewModelScope.launch {
            loading.value = true
            val groupList = getGroupUseCase { groupClickEvent.value = it }
            loading.value = false

            when (groupList) {
                is Result.Success -> groupList.body?.let { groupItems.value = it }
                is Result.Failure -> errorEvent.value =
                    "code: ${groupList.code} message: ${groupList.error}"
                is Result.NetworkError -> errorEvent.value = "네트워크 문제가 발생했습니다."
                is Result.Unexpected -> {
                    Timber.d(groupList.t.toString())
                    errorEvent.value = "알수없는 오류가 발생했습니다."
                }
            }
        }
    }
}