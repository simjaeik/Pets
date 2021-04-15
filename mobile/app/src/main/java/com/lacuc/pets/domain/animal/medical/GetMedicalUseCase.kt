package com.lacuc.pets.domain.animal.medical

import com.lacuc.pets.data.animal.AnimalRepository
import com.lacuc.pets.domain.animal.AnimalDetailMedicalItem
import javax.inject.Inject

class GetMedicalUseCase @Inject constructor(private val repository: AnimalRepository) {
    operator fun invoke(aid: Int): List<AnimalDetailMedicalItem> = repository.loadMedical(aid)
        .map { AnimalDetailMedicalItem(it) }
}