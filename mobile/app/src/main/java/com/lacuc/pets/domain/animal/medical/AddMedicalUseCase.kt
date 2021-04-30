package com.lacuc.pets.domain.animal.medical

import com.lacuc.pets.data.Result
import com.lacuc.pets.data.animal.AnimalRepository
import com.lacuc.pets.data.animal.entity.Medical
import javax.inject.Inject

class AddMedicalUseCase @Inject constructor(private val repository: AnimalRepository) {
    suspend operator fun invoke(aid: Int, medical: Medical): Result<Void> {
        return repository.addMedical(aid, medical)
    }
}