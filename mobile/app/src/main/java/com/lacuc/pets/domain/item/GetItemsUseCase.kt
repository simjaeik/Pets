package com.lacuc.pets.domain.item

import com.lacuc.pets.data.Result
import com.lacuc.pets.data.group.GroupRepository
import javax.inject.Inject

class GetItemHistoryUseCase @Inject constructor(private val repository: GroupRepository) {
    suspend operator fun invoke(
        gid: Int,
        listener: (ItemHistoryItem) -> Unit
    ): Result<List<ItemHistoryItem>> {
        return when (val items = repository.getItems(gid)) {
            is Result.Success -> Result.Success(items.body?.map { ItemHistoryItem(it, listener) })
            is Result.Failure -> items
            is Result.NetworkError -> items
            is Result.Unexpected -> items
        }
    }
}