package com.lacuc.pets.domain.group

import com.lacuc.pets.data.Result
import com.lacuc.pets.data.group.GroupRepository
import com.lacuc.pets.data.group.entity.Member
import javax.inject.Inject

class GetGroupMemberUseCase @Inject constructor(private val repository: GroupRepository) {
    suspend operator fun invoke(gid: Int): Result<List<Member>> {
        return repository.getGroupMembers(gid)
    }
}
