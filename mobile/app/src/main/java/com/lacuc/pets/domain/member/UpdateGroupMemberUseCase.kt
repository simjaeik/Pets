package com.lacuc.pets.domain.member

import com.lacuc.pets.data.Result
import com.lacuc.pets.data.group.GroupRepository
import javax.inject.Inject

class UpdateGroupMemberUseCase @Inject constructor(private val repository: GroupRepository) {
    // TODO: 2021-05-11 gid와 uid를 전달해야 할까? 프로필 정보는 토큰으로 가능할 듯 한데
    suspend operator fun invoke(gid: Int, uid: Int, name: String, email: String): Result<Void> {
        return repository.updateGroupMember(gid, uid, name, email)
    }
}
