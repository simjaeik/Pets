package com.lacuc.pets.ui.manage.animal.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lacuc.pets.data.Result
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

    val animalName = MutableLiveData("")

    val animalImage = MutableLiveData("")

    val loading = MutableLiveData(false)

    val tabPosition = MutableLiveData(0)

    val detailItemClickEvent = SingleLiveEvent<AnimalDetailItem>()

    fun onTabSelect(position: Int) {
        tabPosition.value = position
        loadDetailItem()
    }

    fun loadDetailItem() {
        viewModelScope.launch {
            loading.value = true
            val itemList = when (tabPosition.value) {
                0 -> getAnimalDetailUseCase(aid)
                1 -> getMedicalUseCase(aid)
                2 -> getMemoUseCase(aid) { detailItemClickEvent.value = it }
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
            val animal = getAnimalUseCase(aid)

            when (animal) {
                is Result.Success -> animal.body?.let {
                    animalName.value = it.name
                    animalImage.value = it.image
                }
                is Result.Failure -> errorEvent.value =
                    "code: ${animal.code} message: ${animal.error}"
                is Result.NetworkError -> errorEvent.value = "네트워크 문제가 발생했습니다."
                is Result.Unexpected -> {
                    Timber.d(animal.t.toString())
                    errorEvent.value = "알수없는 오류가 발생했습니다."
                }
            }
        }
    }
}