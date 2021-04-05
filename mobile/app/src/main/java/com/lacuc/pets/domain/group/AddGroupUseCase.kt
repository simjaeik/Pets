package com.lacuc.pets.domain.group

import com.lacuc.pets.data.group.Group
import com.lacuc.pets.data.group.GroupRepository
import javax.inject.Inject

class AddGroupUseCase @Inject constructor(private val repository: GroupRepository) {
    operator fun invoke(email: String, group: Group) {
        repository.saveGroup(email, group)
    }
}
