package com.lacuc.pets.domain.animal.memo

import com.lacuc.pets.data.Result
import com.lacuc.pets.data.animal.AnimalRepository
import javax.inject.Inject

class UpdateMemoUseCase @Inject constructor(private val repository: AnimalRepository) {
    suspend operator fun invoke(mid: String, gid: String, content: String): Result<Void> {
        return repository.updateMemo(mid, gid, content)
    }
}