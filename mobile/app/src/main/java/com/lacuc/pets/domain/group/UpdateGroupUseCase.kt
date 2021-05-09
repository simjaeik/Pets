package com.lacuc.pets.domain.group

import com.lacuc.pets.data.Result
import com.lacuc.pets.data.group.GroupRepository
import com.lacuc.pets.data.group.entity.Group
import javax.inject.Inject

class UpdateGroupUseCase @Inject constructor(private val repository: GroupRepository) {
    suspend operator fun invoke(group: Group): Result<Void> {
        return repository.updateGroup(group)
    }
}
