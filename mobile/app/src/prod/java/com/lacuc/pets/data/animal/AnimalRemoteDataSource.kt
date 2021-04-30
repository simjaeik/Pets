package com.lacuc.pets.data.animal

import com.lacuc.pets.data.Result
import com.lacuc.pets.data.animal.entity.Animal
import com.lacuc.pets.data.animal.entity.Medical
import com.lacuc.pets.data.animal.entity.Memo
import javax.inject.Inject

class AnimalRemoteDataSource @Inject constructor(
    private val animalService: AnimalService
) : AnimalDataSource {
    override suspend fun getAnimalByGroup(gid: Int): Result<List<Animal>> =
        animalService.getAnimalByGroup(gid)

    override suspend fun addAnimal(animal: Animal): Result<Void> = animalService.addAnimal(animal)

    override suspend fun deleteAnimal(aid: Int): Result<Void> {
        return animalService.deleteAnimal(aid)
    }

    override suspend fun getAnimal(aid: Int): Result<Animal> {
        return animalService.getAnimal(aid)
    }

    override suspend fun updateAnimalDetail(aid: Int, animal: Animal): Result<Void> {
        return animalService.updateAnimalDetail(aid, animal)
    }

    override suspend fun getMemos(aid: Int): Result<List<Memo>> {
        return animalService.getMemos(aid)
    }

    override suspend fun setMemo(aid: Int, memo: Memo): Result<Void> {
        return animalService.setMemo(aid, memo)
    }

    override suspend fun updateMemo(mid: Int, memo: Memo): Result<Void> {
        return animalService.updateMemo(mid, memo)
    }

    override suspend fun deleteMemo(mid: Int): Result<Void> {
        return animalService.deleteMemo(mid)
    }

    override suspend fun loadMedical(aid: Int): Result<List<Medical>> {
        TODO("Not yet implemented")
    }

    override suspend fun addMedical(aid: Int, medical: Medical): Result<Void> {
        TODO("Not yet implemented")
    }
}