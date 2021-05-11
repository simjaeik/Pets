package com.lacuc.pets.domain.group

import com.lacuc.pets.data.Result
import com.lacuc.pets.data.group.GroupRepository
import javax.inject.Inject

class GetGroupsUseCase @Inject constructor(private val repository: GroupRepository) {
    suspend operator fun invoke(listener: (GroupItem) -> Unit): Result<List<GroupItem>> {
        return when (val groups = repository.getMyGroups()) {
            is Result.Success -> Result.Success(groups.body?.map { GroupItem(it, listener) })
            is Result.Failure -> groups
            is Result.NetworkError -> groups
            is Result.Unexpected -> groups
        }
    }
}