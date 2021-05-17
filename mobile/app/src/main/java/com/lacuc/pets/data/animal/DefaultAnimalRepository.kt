package com.lacuc.pets.data.animal

import com.lacuc.pets.data.Result
import com.lacuc.pets.data.animal.entity.Animal
import com.lacuc.pets.data.animal.entity.Medical
import com.lacuc.pets.data.animal.entity.Memo
import javax.inject.Inject

class DefaultAnimalRepository @Inject constructor(
    private val animalRemoteDataSource: AnimalDataSource
) : AnimalRepository {
    override suspend fun getAnimalByGroup(gid: String): Result<List<Animal>> =
        animalRemoteDataSource.getAnimalByGroup(gid)

    override suspend fun addAnimal(animal: Animal): Result<Void> =
        animalRemoteDataSource.addAnimal(animal)

    override suspend fun loadMedical(aid: String): Result<List<Medical>> =
        animalRemoteDataSource.loadMedical(aid)

    override suspend fun getMemos(aid: String): Result<List<Memo>> =
        animalRemoteDataSource.getMemos(aid)

    override suspend fun addMedical(aid: String, medical: Medical): Result<Void> =
        animalRemoteDataSource.addMedical(aid, medical)

    override suspend fun getMemo(aid: String, mid: String): Result<Memo> =
        animalRemoteDataSource.getMemo(aid, mid)

    override suspend fun setMemo(aid: String, memo: Memo): Result<Void> =
        animalRemoteDataSource.setMemo(aid, memo)

    override suspend fun deleteAnimal(aid: String): Result<Void> {
        TODO("Not yet implemented")
    }

    override suspend fun getAnimal(aid: String): Result<Animal> =
        animalRemoteDataSource.getAnimal(aid)

    override suspend fun updateAnimalDetail(aid: String, animal: Animal): Result<Void> =
        animalRemoteDataSource.updateAnimalDetail(aid, animal)

    override suspend fun updateMemo(mid: String, gid: String, content: String): Result<Void> =
        animalRemoteDataSource.updateMemo(mid, gid, content)

    override suspend fun deleteMemo(mid: String): Result<Void> =
        animalRemoteDataSource.deleteMemo(mid)
}