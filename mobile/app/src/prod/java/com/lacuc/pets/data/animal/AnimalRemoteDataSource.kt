package com.lacuc.pets.data.animal

import com.lacuc.pets.data.Result
import com.lacuc.pets.data.animal.entity.Animal
import com.lacuc.pets.data.animal.entity.Medical
import com.lacuc.pets.data.animal.entity.Memo
import javax.inject.Inject

class AnimalRemoteDataSource @Inject constructor(
    private val animalService: AnimalService
) : AnimalDataSource {
    override suspend fun getAnimalByGroup(gid: String): Result<List<Animal>> =
        animalService.getAnimalByGroup(gid)

    override suspend fun addAnimal(animal: Animal): Result<Void> = animalService.addAnimal(animal)

    override suspend fun deleteAnimal(aid: String): Result<Void> {
        return animalService.deleteAnimal(aid)
    }

    override suspend fun getAnimal(aid: String): Result<Animal> {
        return animalService.getAnimal(aid)
    }

    override suspend fun updateAnimalDetail(aid: String, animal: Animal): Result<Void> {
        return animalService.updateAnimalDetail(aid, animal)
    }

    override suspend fun getMemos(aid: String): Result<List<Memo>> {
        return animalService.getMemos(aid)
    }

    override suspend fun setMemo(aid: String, memo: Memo): Result<Void> {
        return animalService.setMemo(aid, memo)
    }

    override suspend fun updateMemo(mid: String, gid: String, content: String): Result<Void> {
        return animalService.updateMemo(mid, gid, content)
    }

    override suspend fun deleteMemo(mid: String): Result<Void> {
        return animalService.deleteMemo(mid)
    }

    override suspend fun loadMedical(aid: String): Result<List<Medical>> {
        return animalService.getMedicalHistories(aid)
    }

    override suspend fun addMedical(aid: String, params: Map<String, String>): Result<Void> {
        return animalService.setMedicalHistory(aid, params)
    }

    override suspend fun getMemo(aid: String, mid: String): Result<Memo> {
        TODO("Not yet implemented")
    }

    override suspend fun getMedical(aid: String, hid: String): Result<Medical> {
        TODO("Not yet implemented")
    }

    override suspend fun updateMedical(hid: String, params: Map<String, String>): Result<Void> {
        return animalService.updateMedicalHistory(hid, params)
    }
}