package com.lacuc.pets.domain.group

import com.lacuc.pets.data.Result
import com.lacuc.pets.data.group.GroupRepository
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
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

        val imageFile = File(imageUri)
        val imageBody = imageFile.asRequestBody("image/*".toMediaTypeOrNull())
        val imagePart = MultipartBody.Part.createFormData("img", imageFile.name, imageBody)

        return repository.setGroup(partMap, imagePart)
    }
}
