package com.lacuc.pets.domain.animal

import com.lacuc.pets.data.animal.AnimalRepository

class GetAnimalUseCase(private val repository: AnimalRepository) {
    operator fun invoke(gid: Int, listener: (AnimalItem) -> Unit): List<AnimalItem> =
        repository.loadAnimal(gid).map { AnimalItem(it, listener) }
}