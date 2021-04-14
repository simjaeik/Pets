package com.lacuc.pets.ui.animal.add

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lacuc.pets.data.animal.Animal
import com.lacuc.pets.domain.animal.animal.AddAnimalUseCase
import com.lacuc.pets.util.SingleLiveEvent
import com.lacuc.pets.util.safeValue
import javax.inject.Inject

class AddAnimalViewModel @Inject constructor(
    private val addAnimalUseCase: AddAnimalUseCase
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
        addAnimalUseCase(
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
        completeEvent.value = Unit
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