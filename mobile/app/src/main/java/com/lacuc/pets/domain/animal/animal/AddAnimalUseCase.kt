package com.lacuc.pets.domain.animal.animal

import com.lacuc.pets.data.animal.Animal
import com.lacuc.pets.data.animal.AnimalRepository
import javax.inject.Inject

class AddAnimalUseCase @Inject constructor(private val repository: AnimalRepository) {
    operator fun invoke(animal: Animal) {
        repository.addAnimal(animal)
    }
}