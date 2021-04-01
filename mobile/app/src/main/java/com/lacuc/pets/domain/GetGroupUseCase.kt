package com.lacuc.pets.domain

import com.lacuc.pets.data.GroupRepository

class GetGroupUseCase(
    private val repository: GroupRepository
) {
    operator fun invoke(): List<GroupItem> =
        repository.loadGroup()
            .map { GroupItem(it) }
}