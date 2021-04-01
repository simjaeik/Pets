package com.lacuc.pets.domain.group

import com.lacuc.pets.data.GroupRepository

class GetGroupUseCase(
    private val repository: GroupRepository
) {
    operator fun invoke(email: String, listener: (GroupItem) -> Unit): List<GroupItem> =
        repository.loadGroup(email)
            .map { GroupItem(it, listener) }
}