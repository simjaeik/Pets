package com.lacuc.pets.ui.manage.animal.save

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lacuc.pets.data.Result
import com.lacuc.pets.data.animal.entity.Animal
import com.lacuc.pets.domain.animal.animal.AddAnimalUseCase
import com.lacuc.pets.domain.animal.animal.GetAnimalUseCase
import com.lacuc.pets.domain.animal.animal.UpdateAnimalUseCase
import com.lacuc.pets.util.SingleLiveEvent
import com.lacuc.pets.util.safeValue
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class SaveAnimalViewModel @Inject constructor(
    private val addAnimalUseCase: AddAnimalUseCase,
    private val updateAnimalUseCase: UpdateAnimalUseCase,
    private val getAnimalUseCase: GetAnimalUseCase,
    private val errorEvent: SingleLiveEvent<String>
) : ViewModel() {

    var gid = ""
    var aid = ""
    val name = MutableLiveData("")
    val image = MutableLiveData("")
    val age = MutableLiveData("")
    val sex = MutableLiveData("")
    val species = MutableLiveData("")
    val subspecies = MutableLiveData("")
    val weight = MutableLiveData("")
    val number = MutableLiveData("")

    var isUpdate = false

    val completeEvent = SingleLiveEvent<Unit>()
    val updateEvent = SingleLiveEvent<Unit>()

    fun setImage(dataString: String?) {
        image.value = dataString
    }

    fun saveAnimal() {
        viewModelScope.launch {
            val animal = createAnimal()
            val result = if (isUpdate)
                updateAnimalUseCase(aid, gid, animal)
            else addAnimalUseCase(gid, image.safeValue, animal)

            when (result) {
                is Result.Success ->
                    if (isUpdate) updateEvent.value = Unit else completeEvent.value = Unit
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

    private fun createAnimal() = Animal(
        aid,
        gid,
        name.safeValue,
        image.safeValue,
        age.safeValue,
        sex.safeValue,
        species.safeValue,
        subspecies.safeValue,
        weight.safeValue,
        number.safeValue
    )

    fun loadAnimal() {
        viewModelScope.launch {
            val animal = getAnimalUseCase(aid)

            when (animal) {
                is Result.Success -> initProperties(animal.body)
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

    private fun initProperties(animal: Animal?) {
        if (animal == null)
            return

        isUpdate = true
        name.value = animal.name
        image.value = animal.image
        age.value = animal.age
        sex.value = animal.sex
        species.value = animal.species
        subspecies.value = animal.subspecies
        weight.value = animal.weight
        number.value = animal.number
    }

}