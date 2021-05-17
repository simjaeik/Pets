package com.lacuc.pets.domain.animal.medical

import com.lacuc.pets.data.Result
import com.lacuc.pets.data.animal.AnimalRepository
import javax.inject.Inject

class AddMedicalUseCase @Inject constructor(private val repository: AnimalRepository) {
    suspend operator fun invoke(aid: String, params: Map<String, String>): Result<Void> {
        return repository.addMedical(aid, params)
    }
}