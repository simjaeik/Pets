package com.lacuc.pets.domain.group

import com.lacuc.pets.data.Result
import com.lacuc.pets.data.group.GroupRepository
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.util.regex.Pattern
import javax.inject.Inject

class UpdateGroupUseCase @Inject constructor(private val repository: GroupRepository) {
    suspend operator fun invoke(
        gid: String,
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
        return if (Pattern.matches("^(http)", imageUri)) {
            val imagePart = MultipartBody.Part.createFormData("image", imageUri)
            repository.updateGroup(gid, partMap, imagePart)
        } else {
            val imageFile = File(imageUri)
            val imageBody = imageFile.asRequestBody("image/*".toMediaTypeOrNull())
            val imagePart = MultipartBody.Part.createFormData("image", imageFile.name, imageBody)

            repository.updateGroup(gid, partMap, imagePart)
        }
    }
}
