package com.lacuc.pets.ui.manage.animal.add

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lacuc.pets.data.Result
import com.lacuc.pets.data.animal.entity.Animal
import com.lacuc.pets.domain.animal.animal.AddAnimalUseCase
import com.lacuc.pets.util.SingleLiveEvent
import com.lacuc.pets.util.safeValue
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class AddAnimalViewModel @Inject constructor(
    private val addAnimalUseCase: AddAnimalUseCase,
    private val errorEvent: SingleLiveEvent<String>
) : ViewModel() {

    val name = MutableLiveData("")
    val image = MutableLiveData("")
    val age = MutableLiveData<String>()
    val sex = MutableLiveData("")
    val species = MutableLiveData("")
    val subspecies = MutableLiveData("")
    val weight = MutableLiveData<String>()
    val number = MutableLiveData("")

    val completeEvent = SingleLiveEvent<Unit>()

    fun setImage(dataString: String?) {
        image.value = dataString
    }

    fun saveAnimal() {
        viewModelScope.launch {
            val result = addAnimalUseCase(
                Animal(
                    1,
                    name.safeValue,
                    image.safeValue,
                    if (age.value.isNullOrEmpty()) 0 else age.safeValue.toInt(),
                    sex.safeValue,
                    species.safeValue,
                    subspecies.safeValue,
                    if (weight.value.isNullOrEmpty()) 0.0 else age.safeValue.toDouble(),
                    number.safeValue
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

    fun initData(animal: Animal?) {
        if (animal == null)
            return

        name.value = animal.name
        image.value = animal.image
        age.value = animal.age.toString()
        sex.value = animal.sex
        species.value = animal.species
        subspecies.value = animal.subspecies
        weight.value = animal.weight.toString()
        number.value = animal.number
    }

}