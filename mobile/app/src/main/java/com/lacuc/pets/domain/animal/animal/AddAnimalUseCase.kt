package com.lacuc.pets.domain.animal.animal

import com.lacuc.pets.data.Result
import com.lacuc.pets.data.animal.AnimalRepository
import com.lacuc.pets.data.animal.entity.Animal
import javax.inject.Inject

class AddAnimalUseCase @Inject constructor(private val repository: AnimalRepository) {
    suspend operator fun invoke(animal: Animal): Result<Void> {
        return repository.addAnimal(animal)
    }
}