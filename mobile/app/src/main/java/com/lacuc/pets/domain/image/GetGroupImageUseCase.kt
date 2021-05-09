package com.lacuc.pets.domain.image

import com.lacuc.pets.data.Result
import com.lacuc.pets.data.group.GroupRepository
import com.lacuc.pets.data.group.entity.GroupImage
import javax.inject.Inject

class GetGroupImageUseCase @Inject constructor(private val repository: GroupRepository) {
    suspend operator fun invoke(gid: Int, iid: Int): Result<GroupImage> {
        return repository.getGroupImage(gid, iid)
    }
}