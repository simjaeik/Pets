package com.lacuc.pets.domain.animal

import com.lacuc.pets.data.animal.Animal
import com.lacuc.pets.data.animal.AnimalRepository

class GetAnimalUseCase(private val repository: AnimalRepository) {
    operator fun invoke(gid: Int): List<Animal> = repository.loadAnimal(gid)
}