package com.lacuc.pets.domain.group

import com.lacuc.pets.data.Result
import com.lacuc.pets.data.group.GroupRepository
import com.lacuc.pets.data.group.entity.Group
import javax.inject.Inject

class GetGroupUseCase @Inject constructor(private val repository: GroupRepository) {
    suspend operator fun invoke(gid: String): Result<Group> {
        return repository.getGroup(gid)
    }
}