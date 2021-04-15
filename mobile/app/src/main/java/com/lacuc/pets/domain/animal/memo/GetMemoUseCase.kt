package com.lacuc.pets.domain.animal.memo

import com.lacuc.pets.data.animal.AnimalRepository
import com.lacuc.pets.domain.animal.AnimalDetailMemoItem
import javax.inject.Inject

class GetMemoUseCase @Inject constructor(private val repository: AnimalRepository) {
    operator fun invoke(aid: Int): List<AnimalDetailMemoItem> = repository.loadMemo(aid)
        .map { AnimalDetailMemoItem(it) }
}