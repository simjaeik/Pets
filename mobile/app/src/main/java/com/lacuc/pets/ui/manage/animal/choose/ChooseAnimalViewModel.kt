package com.lacuc.pets.ui.manage.animal.choose

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lacuc.pets.data.Result
import com.lacuc.pets.domain.animal.AnimalItem
import com.lacuc.pets.domain.animal.animal.GetAnimalUseCase
import com.lacuc.pets.util.SingleLiveEvent
import kotlinx.coroutines.launch
import javax.inject.Inject

class ChooseAnimalViewModel @Inject constructor(
    val getAnimalUseCase: GetAnimalUseCase
) : ViewModel() {

    val animalItems = MutableLiveData<List<AnimalItem>>()

    val loading = MutableLiveData(false)

    val animalClickEvent = SingleLiveEvent<AnimalItem>()

    init {
        loadGroups()
    }

    fun loadGroups() {
        viewModelScope.launch {
            loading.value = true
            val animalList = getAnimalUseCase(1) { animalClickEvent.value = it }
            loading.value = false

            when (animalList) {
                is Result.Success -> animalList.body?.let { animalItems.value = it }
                else -> TODO("Not Implemented.")
            }
        }
    }
}