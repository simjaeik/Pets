package com.lacuc.pets.domain.member

import com.lacuc.pets.data.Result
import com.lacuc.pets.data.group.GroupRepository
import javax.inject.Inject

class UpdateGroupMemberUseCase @Inject constructor(private val repository: GroupRepository) {
    suspend operator fun invoke(gid: Int, uid: Int, name: String, email: String): Result<Void> {
        return repository.updateGroupMember(gid, uid, name, email)
    }
}
