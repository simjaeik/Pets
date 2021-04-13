package com.lacuc.pets.domain.animal

import com.lacuc.pets.data.animal.AnimalRepository
import com.lacuc.pets.ui.animal.detail.AnimalDetailMedicalItem
import javax.inject.Inject

class GetMedicalUseCase @Inject constructor(private val repository: AnimalRepository) {
    operator fun invoke(aid: Int): List<AnimalDetailMedicalItem> = repository.loadMedical(aid)
        .map { AnimalDetailMedicalItem(it) }
}