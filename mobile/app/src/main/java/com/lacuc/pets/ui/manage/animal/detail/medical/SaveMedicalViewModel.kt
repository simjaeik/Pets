package com.lacuc.pets.ui.manage.animal.detail.medical

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lacuc.pets.data.Result
import com.lacuc.pets.domain.animal.medical.AddMedicalUseCase
import com.lacuc.pets.domain.animal.medical.GetMedicalUseCase
import com.lacuc.pets.domain.animal.medical.UpdateMedicalUseCase
import com.lacuc.pets.util.SingleLiveEvent
import com.lacuc.pets.util.safeValue
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class SaveMedicalViewModel @Inject constructor(
    private val getMedicalUseCase: GetMedicalUseCase,
    private val updateMedicalUseCase: UpdateMedicalUseCase,
    private val addMedicalUseCase: AddMedicalUseCase,
    private val errorEvent: SingleLiveEvent<String>
) : ViewModel() {

    var gid = ""
    var aid = ""
    var hid = ""

    val title = MutableLiveData("")
    val hospital = MutableLiveData("")
    val time = MutableLiveData("")
    val content = MutableLiveData("")

    val completeEvent = SingleLiveEvent<Unit>()

    private var isUpdate = false

    fun onCompleteClick() {
        viewModelScope.launch {
            val result = if (isUpdate)
                updateMedicalUseCase(hid, createParams())
            else
                addMedicalUseCase(aid, createParams())

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

    private fun createParams(): Map<String, String> = mapOf(
        "GID" to gid,
        "date" to time.safeValue,
        "title" to title.safeValue,
        "content" to content.safeValue,
        "hospital" to hospital.safeValue
    )

    fun loadMedical() {
        viewModelScope.launch {
            val medical = getMedicalUseCase(aid, hid)

            when (medical) {
                is Result.Success -> medical.body?.let {
                    isUpdate = true
                    title.value = it.title
                    time.value = it.date.toString()
                    content.value = it.content
                    hospital.value = it.hospital
                }
                is Result.Failure -> errorEvent.value =
                    "code: ${medical.code} message: ${medical.error}"
                is Result.NetworkError -> errorEvent.value = "네트워크 문제가 발생했습니다."
                is Result.Unexpected -> {
                    Timber.d(medical.t.toString())
                    errorEvent.value = "알수없는 오류가 발생했습니다."
                }
            }
        }
    }
}