package com.lacuc.pets.domain.group

import com.lacuc.pets.data.Result
import com.lacuc.pets.data.group.GroupRepository
import com.lacuc.pets.data.group.entity.GroupImage
import javax.inject.Inject

class GetGroupImagesUseCase @Inject constructor(private val repository: GroupRepository) {
    suspend operator fun invoke(gid: Int): Result<List<GroupImage>> {
        return repository.getGroupImages(gid)
    }
}
