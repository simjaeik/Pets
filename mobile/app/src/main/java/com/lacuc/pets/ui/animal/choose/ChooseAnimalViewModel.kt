package com.lacuc.pets.ui.animal.choose

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lacuc.pets.domain.animal.AnimalItem
import com.lacuc.pets.domain.animal.animal.GetAnimalUseCase
import com.lacuc.pets.util.SingleLiveEvent
import javax.inject.Inject

class ChooseAnimalViewModel @Inject constructor(
    val getAnimalUseCase: GetAnimalUseCase
) : ViewModel() {

    val animalItems = MutableLiveData<List<AnimalItem>>()

    val animalClickEvent = SingleLiveEvent<AnimalItem>()

    init {
        loadGroups()
    }

    fun loadGroups() {
        animalItems.value = getAnimalUseCase(1) {
            animalClickEvent.value = it
        }
    }
}