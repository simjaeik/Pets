package com.lacuc.pets.domain.item

import com.lacuc.pets.data.Result
import com.lacuc.pets.data.group.GroupRepository
import javax.inject.Inject

class AddItemUseCase @Inject constructor(private val repository: GroupRepository) {
    suspend operator fun invoke(params: Map<String, String>): Result<Void> {
        return repository.setItem(params)
    }
}