package com.lacuc.pets.domain.animal.memo

import com.lacuc.pets.data.Result
import com.lacuc.pets.data.animal.AnimalRepository
import com.lacuc.pets.data.animal.entity.Memo
import javax.inject.Inject

class GetMemoUseCase @Inject constructor(private val repository: AnimalRepository) {
    suspend operator fun invoke(aid: String, mid: String): Result<Memo> {
        return repository.getMemo(aid, mid)
    }
}