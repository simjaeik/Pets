package com.lacuc.pets.domain.image

import com.lacuc.pets.data.Result
import com.lacuc.pets.data.group.GroupRepository
import javax.inject.Inject

class UpdateImageUseCase @Inject constructor(private val repository: GroupRepository) {
    suspend operator fun invoke(param: Map<String, Any>): Result<Void> {
        return repository.updateGroupImage(param)
    }
}