package com.lacuc.pets.domain.animal.medical

import com.lacuc.pets.data.animal.AnimalRepository
import com.lacuc.pets.data.animal.Medical
import javax.inject.Inject

class AddMedicalUseCase @Inject constructor(private val repository: AnimalRepository) {
    operator fun invoke(medical: Medical) {
        repository.addMedical(medical)
    }
}