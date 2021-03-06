package com.lacuc.pets.domain.animal.memo

import com.lacuc.pets.data.Result
import com.lacuc.pets.data.animal.AnimalRepository
import com.lacuc.pets.domain.animal.AnimalDetailMemoItem
import javax.inject.Inject

class GetMemosUseCase @Inject constructor(private val repository: AnimalRepository) {
    suspend operator fun invoke(
        aid: String,
        listener: (AnimalDetailMemoItem) -> Unit
    ): Result<List<AnimalDetailMemoItem>> {
        return when (val memos = repository.getMemos(aid)) {
            is Result.Success ->
                Result.Success(memos.body?.map { AnimalDetailMemoItem(it, listener) })
            is Result.Failure -> memos
            is Result.NetworkError -> memos
            is Result.Unexpected -> memos
        }
    }
}