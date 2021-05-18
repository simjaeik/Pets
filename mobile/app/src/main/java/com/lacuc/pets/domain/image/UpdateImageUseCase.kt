package com.lacuc.pets.domain.image

import com.lacuc.pets.data.Result
import com.lacuc.pets.data.group.GroupRepository
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject

class UpdateImageUseCase @Inject constructor(private val repository: GroupRepository) {
    suspend operator fun invoke(iid: String, imageUri: String, tag: String): Result<Void> {
        return if (imageUri.startsWith("http")) {
            repository.updateGroupImage(iid, tag)
        } else {
            val imageFile = File(imageUri)
            val imageBody = imageFile.asRequestBody("image/*".toMediaTypeOrNull())
            val imagePart = MultipartBody.Part.createFormData("img", imageFile.name, imageBody)

            repository.updateGroupImage(iid, tag, imagePart)
        }
    }
}