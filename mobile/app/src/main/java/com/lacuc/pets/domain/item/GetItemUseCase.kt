package com.lacuc.pets.domain.item

import com.lacuc.pets.data.Result
import com.lacuc.pets.data.group.GroupRepository
import com.lacuc.pets.data.group.entity.ItemHistory
import javax.inject.Inject

class GetItemUseCase @Inject constructor(private val repository: GroupRepository) {
    suspend operator fun invoke(hid: String): Result<ItemHistory> {
        return repository.getItem(hid)
    }
}