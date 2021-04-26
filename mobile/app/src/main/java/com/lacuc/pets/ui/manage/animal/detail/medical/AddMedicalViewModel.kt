package com.lacuc.pets.ui.manage.animal.detail.medical

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lacuc.pets.data.Result
import com.lacuc.pets.data.animal.entity.Medical
import com.lacuc.pets.domain.animal.medical.AddMedicalUseCase
import com.lacuc.pets.util.SingleLiveEvent
import com.lacuc.pets.util.safeValue
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddMedicalViewModel @Inject constructor(
    val addMedicalUseCase: AddMedicalUseCase
) : ViewModel() {

    val title = MutableLiveData("")
    val hospital = MutableLiveData("")
    val time = MutableLiveData("")
    val content = MutableLiveData("")

    val completeEvent = SingleLiveEvent<Unit>()

    fun onCompleteClick() {
        viewModelScope.launch {
            val result = addMedicalUseCase(
                1, Medical(
                    System.currentTimeMillis(),
                    title.safeValue,
                    content.safeValue,
                    hospital.safeValue
                )
            )

            when (result) {
                is Result.Success -> completeEvent.value = Unit
                else -> TODO("Not Implemented")
            }
        }
    }
}