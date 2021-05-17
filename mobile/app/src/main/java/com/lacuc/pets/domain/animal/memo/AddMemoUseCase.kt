package com.lacuc.pets.domain.animal.memo

import com.lacuc.pets.data.Result
import com.lacuc.pets.data.animal.AnimalRepository
import javax.inject.Inject

class AddMemoUseCase @Inject constructor(private val repository: AnimalRepository) {
    suspend operator fun invoke(aid: String, gid: String, content: String): Result<Void> {
        return repository.setMemo(aid, gid, content)
    }
}