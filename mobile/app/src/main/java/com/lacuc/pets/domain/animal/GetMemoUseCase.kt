package com.lacuc.pets.domain.animal

import com.lacuc.pets.data.animal.AnimalRepository
import com.lacuc.pets.ui.animal.detail.AnimalDetailMemoItem
import javax.inject.Inject

class GetMemoUseCase @Inject constructor(private val repository: AnimalRepository) {
    operator fun invoke(aid: Int): List<AnimalDetailMemoItem> = repository.loadMemo(aid)
        .map { AnimalDetailMemoItem(it) }
}