package com.lacuc.pets.domain.member

import com.lacuc.pets.data.Result
import com.lacuc.pets.data.group.GroupRepository
import com.lacuc.pets.data.group.entity.Member
import javax.inject.Inject

class GetProfileUseCase @Inject constructor(private val repository: GroupRepository) {
    suspend operator fun invoke(): Result<Member> {
        return repository.getProfile()
    }
}
