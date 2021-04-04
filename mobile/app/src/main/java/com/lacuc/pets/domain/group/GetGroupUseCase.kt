package com.lacuc.pets.domain.group

import com.lacuc.pets.data.GroupRepository
import javax.inject.Inject

class GetGroupUseCase @Inject constructor(private val repository: GroupRepository) {
    operator fun invoke(email: String, listener: (GroupItem) -> Unit): List<GroupItem> =
        repository.loadGroup(email)
            .map { GroupItem(it, listener) }
}