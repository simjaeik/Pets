package com.lacuc.pets.domain.animal.medical

import com.lacuc.pets.data.Result
import com.lacuc.pets.data.animal.AnimalRepository
import com.lacuc.pets.domain.animal.AnimalDetailMedicalItem
import javax.inject.Inject

class GetMedicalsUseCase @Inject constructor(private val repository: AnimalRepository) {
    suspend operator fun invoke(
        aid: String,
        listener: (AnimalDetailMedicalItem) -> Unit
    ): Result<List<AnimalDetailMedicalItem>> {
        return when (val medicals = repository.loadMedical(aid)) {
            is Result.Success ->
                Result.Success(medicals.body?.map { AnimalDetailMedicalItem(it, listener) })
            is Result.Failure -> medicals
            is Result.NetworkError -> medicals
            is Result.Unexpected -> medicals
        }
    }
}