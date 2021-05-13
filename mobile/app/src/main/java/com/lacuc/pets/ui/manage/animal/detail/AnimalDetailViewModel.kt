package com.lacuc.pets.ui.manage.animal.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lacuc.pets.data.Result
import com.lacuc.pets.data.animal.entity.Animal
import com.lacuc.pets.domain.animal.AnimalDetailItem
import com.lacuc.pets.domain.animal.animal.GetAnimalDetailUseCase
import com.lacuc.pets.domain.animal.animal.GetAnimalUseCase
import com.lacuc.pets.domain.animal.medical.GetMedicalUseCase
import com.lacuc.pets.domain.animal.memo.GetMemoUseCase
import com.lacuc.pets.util.SingleLiveEvent
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class AnimalDetailViewModel @Inject constructor(
    private val getAnimalUseCase: GetAnimalUseCase,
    private val getAnimalDetailUseCase: GetAnimalDetailUseCase,
    private val getMedicalUseCase: GetMedicalUseCase,
    private val getMemoUseCase: GetMemoUseCase,
    private val errorEvent: SingleLiveEvent<String>
) : ViewModel() {

    var aid = ""

    val detailItems = MutableLiveData<List<AnimalDetailItem>>()

    val animal = MutableLiveData<Animal>()

    val loading = MutableLiveData(false)

    fun loadDetailItem(position: Int) {
        viewModelScope.launch {
            loading.value = true
            val itemList = when (position) {
                0 -> getAnimalDetailUseCase(aid)
                1 -> getMedicalUseCase(aid)
                2 -> getMemoUseCase(aid)
                else -> {
                    loading.value = false
                    return@launch
                }
            }
            loading.value = false

            when (itemList) {
                is Result.Success -> itemList.body?.let { detailItems.value = it }
                is Result.Failure -> errorEvent.value =
                    "code: ${itemList.code} message: ${itemList.error}"
                is Result.NetworkError -> errorEvent.value = "네트워크 문제가 발생했습니다."
                is Result.Unexpected -> {
                    Timber.d(itemList.t.toString())
                    errorEvent.value = "알수없는 오류가 발생했습니다."
                }
            }
        }
    }

    fun loadAnimal() {
        viewModelScope.launch {
            val _animal = getAnimalUseCase(aid)

            when (_animal) {
                is Result.Success -> _animal.body?.let { animal.value = it }
                is Result.Failure -> errorEvent.value =
                    "code: ${_animal.code} message: ${_animal.error}"
                is Result.NetworkError -> errorEvent.value = "네트워크 문제가 발생했습니다."
                is Result.Unexpected -> {
                    Timber.d(_animal.t.toString())
                    errorEvent.value = "알수없는 오류가 발생했습니다."
                }
            }
        }
    }
}