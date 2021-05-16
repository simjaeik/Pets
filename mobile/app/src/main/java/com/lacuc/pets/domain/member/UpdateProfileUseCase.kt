package com.lacuc.pets.domain.member

import com.lacuc.pets.data.Result
import com.lacuc.pets.data.group.GroupRepository
import javax.inject.Inject

class UpdateProfileUseCase @Inject constructor(private val repository: GroupRepository) {
    suspend operator fun invoke(name: String, email: String): Result<Void> {
        return repository.updateProfile(name, email)
    }
}
