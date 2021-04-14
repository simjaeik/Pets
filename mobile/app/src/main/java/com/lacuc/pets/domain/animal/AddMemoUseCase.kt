package com.lacuc.pets.domain.animal

import com.lacuc.pets.data.animal.AnimalRepository
import com.lacuc.pets.data.animal.Memo
import javax.inject.Inject

class AddMemoUseCase @Inject constructor(private val repository: AnimalRepository) {
    operator fun invoke(memo: Memo) {
        repository.addMemo(memo)
    }
}