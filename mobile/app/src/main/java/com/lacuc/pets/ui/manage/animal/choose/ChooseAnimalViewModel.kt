package com.lacuc.pets.ui.manage.animal.choose

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lacuc.pets.data.Result
import com.lacuc.pets.data.group.entity.Group
import com.lacuc.pets.domain.animal.AnimalItem
import com.lacuc.pets.domain.animal.animal.GetAnimalsUseCase
import com.lacuc.pets.domain.group.GetGroupUseCase
import com.lacuc.pets.util.SingleLiveEvent
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class ChooseAnimalViewModel @Inject constructor(
    private val getAnimalsUseCase: GetAnimalsUseCase,
    private val getGroupUseCase: GetGroupUseCase,
    private val errorEvent: SingleLiveEvent<String>
) : ViewModel() {

    var gid = ""

    val group = MutableLiveData<Group>()

    val animalItems = MutableLiveData<List<AnimalItem>>()

    val loading = MutableLiveData(false)

    val animalClickEvent = SingleLiveEvent<AnimalItem>()

    fun loadGroup() {
        viewModelScope.launch {
            val _group = getGroupUseCase(gid)

            when (_group) {
                is Result.Success -> _group.body?.let { group.value = it }
                is Result.Failure -> errorEvent.value =
                    "code: ${_group.code} message: ${_group.error}"
                is Result.NetworkError -> errorEvent.value = "네트워크 문제가 발생했습니다."
                is Result.Unexpected -> {
                    Timber.d(_group.t.toString())
                    errorEvent.value = "알수없는 오류가 발생했습니다."
                }
            }
        }
    }

    fun loadAnimals() {
        viewModelScope.launch {
            loading.value = true
            val animalList = getAnimalsUseCase(gid) { animalClickEvent.value = it }
            loading.value = false

            when (animalList) {
                is Result.Success -> animalList.body?.let { animalItems.value = it }
                is Result.Failure -> errorEvent.value =
                    "code: ${animalList.code} message: ${animalList.error}"
                is Result.NetworkError -> errorEvent.value = "네트워크 문제가 발생했습니다."
                is Result.Unexpected -> {
                    Timber.d(animalList.t.toString())
                    errorEvent.value = "알수없는 오류가 발생했습니다."
                }
            }
        }
    }
}