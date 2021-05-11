package com.lacuc.pets.domain.animal.animal

import com.lacuc.pets.data.Result
import com.lacuc.pets.data.animal.AnimalRepository
import com.lacuc.pets.domain.animal.AnimalDetailDetailItem
import javax.inject.Inject

class GetAnimalDetailUseCase @Inject constructor(private val repository: AnimalRepository) {
    suspend operator fun invoke(aid: Int): Result<List<AnimalDetailDetailItem>> {
        return when (val animal = repository.getAnimal(aid)) {
            is Result.Success -> Result.Success(animal.body?.let { listOf(AnimalDetailDetailItem(it)) })
            is Result.Failure -> animal
            is Result.NetworkError -> animal
            is Result.Unexpected -> animal
        }
    }
}