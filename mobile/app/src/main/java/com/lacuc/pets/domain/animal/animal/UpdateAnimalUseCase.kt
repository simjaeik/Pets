package com.lacuc.pets.domain.animal.animal

import com.lacuc.pets.data.Result
import com.lacuc.pets.data.animal.AnimalRepository
import com.lacuc.pets.data.animal.entity.Animal
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject

class UpdateAnimalUseCase @Inject constructor(private val repository: AnimalRepository) {
    suspend operator fun invoke(
        aid: String,
        gid: String,
        imageUri: String,
        animal: Animal
    ): Result<Void> {
        val partMap = mapOf(
            "GID" to gid,
            "name" to animal.name,
            "age" to animal.age,
            "sex" to animal.sex,
            "image" to animal.image,
            "species" to animal.species,
            "subspecies" to animal.subspecies,
            "weight" to animal.weight,
            "number" to animal.number
        )

        return if (imageUri.startsWith("http")) {
            repository.updateAnimalDetail(aid, partMap)
        } else {
            val imageFile = File(imageUri)
            val imageBody = imageFile.asRequestBody("image/*".toMediaTypeOrNull())
            val imagePart = MultipartBody.Part.createFormData("img", imageFile.name, imageBody)

            repository.updateAnimalDetail(aid, partMap, imagePart)
        }
    }
}