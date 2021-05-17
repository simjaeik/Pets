package com.lacuc.pets.domain.member

import com.lacuc.pets.data.Result
import com.lacuc.pets.data.group.GroupRepository
import javax.inject.Inject

class InviteMemberUseCase @Inject constructor(private val repository: GroupRepository) {
    suspend operator fun invoke(
        gid: String,
        email: String,
        authority: String = "수정권한"
    ): Result<Void> {
        return repository.addGroupMember(gid, email, authority)
    }
}