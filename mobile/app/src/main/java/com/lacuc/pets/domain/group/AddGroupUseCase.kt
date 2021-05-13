package com.lacuc.pets.domain.group

import com.lacuc.pets.data.Result
import com.lacuc.pets.data.group.GroupRepository
import javax.inject.Inject

class AddGroupUseCase @Inject constructor(private val repository: GroupRepository) {
    suspend operator fun invoke(
        name: String,
        info: String,
        imageUri: String,
        share: Boolean
    ): Result<Void> {
        val partMap = mapOf(
            "name" to name,
            "info" to info,
            "share" to share.toString(),
            "latitude" to "0",
            "longitude" to "0"
        )

//        val imageFile = File(imageUri)
//        val imagePart = imageFile.asRequestBody("image/*".toMediaTypeOrNull())
//            .let { MultipartBody.Part.createFormData("image", imageFile.name, it) }

        return repository.setGroup(partMap)
    }
}
