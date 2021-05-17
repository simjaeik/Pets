package com.lacuc.pets.domain.animal.medical

import com.lacuc.pets.data.Result
import com.lacuc.pets.data.animal.AnimalRepository
import javax.inject.Inject

class UpdateMedicalUseCase @Inject constructor(private val repository: AnimalRepository) {
    suspend operator fun invoke(hid: String, params: Map<String, String>): Result<Void> {
        return repository.updateMedical(hid, params)
    }
}