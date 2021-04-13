package com.lacuc.pets.ui.animal.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lacuc.pets.data.animal.Medical
import com.lacuc.pets.domain.animal.medical.AddMedicalUseCase
import com.lacuc.pets.util.SingleLiveEvent
import com.lacuc.pets.util.safeValue
import javax.inject.Inject

class AnimalDetailAddMedicalViewModel @Inject constructor(val addMedicalUseCase: AddMedicalUseCase) :
    ViewModel() {

    val title = MutableLiveData("")
    val hospital = MutableLiveData("")
    val time = MutableLiveData("")
    val content = MutableLiveData("")

    val completeEvent = SingleLiveEvent<Boolean>()

    fun onCompleteClick() {
        addMedicalUseCase(
            Medical(
                System.currentTimeMillis(),
                title.safeValue,
                content.safeValue,
                hospital.safeValue
            )
        )
        completeEvent.value = true
    }
}