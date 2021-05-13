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
import timber.log.Timber
import javax.inject.Inject

class AddMedicalViewModel @Inject constructor(
    private val addMedicalUseCase: AddMedicalUseCase,
    private val errorEvent: SingleLiveEvent<String>
) : ViewModel() {

    var aid = ""
    val title = MutableLiveData("")
    val hospital = MutableLiveData("")
    val time = MutableLiveData("")
    val content = MutableLiveData("")

    val completeEvent = SingleLiveEvent<Unit>()

    fun onCompleteClick() {
        viewModelScope.launch {
            val result = addMedicalUseCase(
                aid, Medical(
                    System.currentTimeMillis(),
                    title.safeValue,
                    content.safeValue,
                    hospital.safeValue
                )
            )

            when (result) {
                is Result.Success -> completeEvent.value = Unit
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