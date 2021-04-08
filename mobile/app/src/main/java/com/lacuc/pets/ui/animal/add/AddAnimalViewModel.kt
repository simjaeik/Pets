package com.lacuc.pets.ui.animal.add

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lacuc.pets.data.animal.Animal
import com.lacuc.pets.domain.animal.AddAnimalUseCase
import com.lacuc.pets.util.SingleLiveEvent
import com.lacuc.pets.util.safeValue
import javax.inject.Inject

class AddAnimalViewModel @Inject constructor(private val addAnimalUseCase: AddAnimalUseCase) :
    ViewModel() {
    val name = MutableLiveData("")

    val image = MutableLiveData("")

    val age = MutableLiveData("")

    val sex = MutableLiveData("")

    val species = MutableLiveData("")

    val subspecies = MutableLiveData("")

    val weight = MutableLiveData("")

    val number = MutableLiveData("")

    val animalUpdated = SingleLiveEvent<Boolean>()

    fun setImage(dataString: String?) {
        image.value = dataString
    }

    fun saveAnimal() {
        addAnimalUseCase(
            Animal(
                1,
                name.safeValue,
                image.safeValue,
                age.safeValue.toInt(),
                sex.safeValue,
                species.safeValue,
                subspecies.safeValue,
                weight.safeValue.toDouble(),
                number.safeValue
            )
        )
        animalUpdated.value = true
    }

}