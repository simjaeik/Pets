package com.lacuc.pets.data.animal

import com.lacuc.pets.data.Result
import com.lacuc.pets.data.animal.entity.Animal
import com.lacuc.pets.data.animal.entity.Medical
import com.lacuc.pets.data.animal.entity.Memo
import javax.inject.Inject

class DefaultAnimalRepository @Inject constructor(
    private val animalRemoteDataSource: AnimalDataSource
) : AnimalRepository {
    override suspend fun getAnimalByGroup(gid: Int): Result<List<Animal>> =
        animalRemoteDataSource.getAnimalByGroup(gid)

    override suspend fun addAnimal(animal: Animal): Result<Void> =
        animalRemoteDataSource.addAnimal(animal)

    override suspend fun loadMedical(aid: Int): Result<List<Medical>> =
        animalRemoteDataSource.loadMedical(aid)

    override suspend fun getMemos(aid: Int): Result<List<Memo>> =
        animalRemoteDataSource.getMemos(aid)

    override suspend fun addMedical(aid: Int, medical: Medical): Result<Void> =
        animalRemoteDataSource.addMedical(aid, medical)

    override suspend fun setMemo(aid: Int, memo: Memo): Result<Void> =
        animalRemoteDataSource.setMemo(aid, memo)

    override suspend fun deleteAnimal(aid: Int): Result<Void> {
        TODO("Not yet implemented")
    }

    override suspend fun getAnimal(aid: Int): Result<Animal> =
        animalRemoteDataSource.getAnimal(aid)

    override suspend fun updateAnimalDetail(aid: Int, animal: Animal): Result<Void> {
        TODO("Not yet implemented")
    }

    override suspend fun updateMemo(mid: Int, memo: Memo): Result<Void> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteMemo(mid: Int): Result<Void> {
        TODO("Not yet implemented")
    }
}