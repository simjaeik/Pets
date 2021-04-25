package com.lacuc.pets.domain.group

import com.lacuc.pets.data.group.GroupRepository
import com.lacuc.pets.data.group.entity.Group
import javax.inject.Inject

class AddGroupUseCase @Inject constructor(private val repository: GroupRepository) {
    suspend operator fun invoke(group: Group) {
        repository.setGroup(group)
    }
}
