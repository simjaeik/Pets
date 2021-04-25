package com.lacuc.pets.ui.manage.group.choose

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lacuc.pets.data.Result
import com.lacuc.pets.domain.group.GetGroupUseCase
import com.lacuc.pets.domain.group.GroupItem
import com.lacuc.pets.util.SingleLiveEvent
import kotlinx.coroutines.launch
import javax.inject.Inject

class ChooseGroupViewModel @Inject constructor(private val getGroupUseCase: GetGroupUseCase) :
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
                else -> TODO("Not Implemented")
            }
        }
    }
}