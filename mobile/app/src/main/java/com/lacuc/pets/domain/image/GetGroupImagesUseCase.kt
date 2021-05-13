package com.lacuc.pets.domain.image

import com.lacuc.pets.data.Result
import com.lacuc.pets.data.group.GroupRepository
import javax.inject.Inject

class GetGroupImagesUseCase @Inject constructor(private val repository: GroupRepository) {
    suspend operator fun invoke(gid: String, listener: (ImageItem) -> Unit): Result<List<ImageItem>> {
        return when (val images = repository.getGroupImages(gid)) {
            is Result.Success -> Result.Success(images.body?.map { ImageItem(it, listener) })
            is Result.Failure -> images
            is Result.NetworkError -> images
            is Result.Unexpected -> images
        }
    }
}