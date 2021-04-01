package com.lacuc.pets.domain

import com.lacuc.pets.data.GroupRepository

class GetGroupUseCase(
    private val repository: GroupRepository,
    private val clickListener: GroupItem.ClickListener
) {
    operator fun invoke(email: String): List<GroupItem> =
        repository.loadGroup(email)
            .map { GroupItem(it, clickListener) }
}