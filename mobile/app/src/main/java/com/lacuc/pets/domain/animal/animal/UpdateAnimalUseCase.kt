package com.lacuc.pets.domain.animal.animal

import com.lacuc.pets.data.Result
import com.lacuc.pets.data.animal.AnimalRepository
import com.lacuc.pets.data.animal.entity.Animal
import javax.inject.Inject

class UpdateAnimalUseCase @Inject constructor(private val repository: AnimalRepository) {
    suspend operator fun invoke(aid: String, gid: String, animal: Animal): Result<Void> {
        return repository.updateAnimalDetail(aid, gid, animal)
    }
}