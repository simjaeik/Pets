package com.lacuc.pets.domain.animal.animal

import com.lacuc.pets.data.Result
import com.lacuc.pets.data.animal.AnimalRepository
import com.lacuc.pets.data.animal.entity.Animal
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject

class AddAnimalUseCase @Inject constructor(private val repository: AnimalRepository) {
    suspend operator fun invoke(gid: String, imageUri: String, animal: Animal): Result<Void> {
        val partMap = mapOf(
            "name" to animal.name,
            "age" to animal.age,
            "sex" to animal.sex,
            "image" to animal.image,
            "species" to animal.species,
            "subspecies" to animal.subspecies,
            "weight" to animal.weight,
            "number" to animal.number
        )

        val imageFile = File(imageUri)
        val imageBody = imageFile.asRequestBody("image/*".toMediaTypeOrNull())
        val imagePart = MultipartBody.Part.createFormData("img", imageFile.name, imageBody)

        return repository.addAnimal(gid, partMap, imagePart)
    }
}