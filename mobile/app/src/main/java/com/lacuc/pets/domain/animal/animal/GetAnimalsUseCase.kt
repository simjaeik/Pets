package com.lacuc.pets.domain.animal.animal

import com.lacuc.pets.data.Result
import com.lacuc.pets.data.animal.AnimalRepository
import com.lacuc.pets.domain.animal.AnimalItem
import javax.inject.Inject

class GetAnimalsUseCase @Inject constructor(private val repository: AnimalRepository) {
    suspend operator fun invoke(
        gid: Int,
        listener: (AnimalItem) -> Unit
    ): Result<List<AnimalItem>> {
        return when (val animals = repository.getAnimalByGroup(gid)) {
            is Result.Success -> Result.Success(animals.body?.map { AnimalItem(it, listener) })
            is Result.Failure -> animals
            is Result.NetworkError -> animals
            is Result.Unexpected -> animals
        }
    }
}