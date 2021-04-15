package com.lacuc.pets.ui.manage.group.choose

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lacuc.pets.domain.group.GetGroupUseCase
import com.lacuc.pets.domain.group.GroupItem
import com.lacuc.pets.util.SingleLiveEvent
import javax.inject.Inject

class ChooseGroupViewModel @Inject constructor(private val getGroupUseCase: GetGroupUseCase) :
    ViewModel() {

    val groupItems = MutableLiveData<List<GroupItem>>()

    val groupClickEvent = SingleLiveEvent<GroupItem>()

    init {
        loadGroups()
    }

    fun loadGroups() {
        // TODO: 2021-04-01 먼저 서버에서 세션 등 사용자를 식별할 수단을 얻어야 한다. 여기서는 임시로 사용함.
        groupItems.value = getGroupUseCase("tempUser@lacuc.com") {
            groupClickEvent.value = it
        }
    }
}