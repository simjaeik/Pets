package com.lacuc.pets.data.animal

import com.lacuc.pets.data.Result
import com.lacuc.pets.data.animal.entity.Animal
import com.lacuc.pets.data.animal.entity.Medical
import com.lacuc.pets.data.animal.entity.Memo
import okhttp3.MultipartBody
import javax.inject.Inject

class DefaultAnimalRepository @Inject constructor(
    private val animalRemoteDataSource: AnimalDataSource
) : AnimalRepository {
    override suspend fun getAnimalByGroup(gid: String): Result<List<Animal>> =
        animalRemoteDataSource.getAnimalByGroup(gid)

    override suspend fun addAnimal(
        gid: String,
        params: Map<String, String>,
        imageFile: MultipartBody.Part
    ): Result<Void> =
        animalRemoteDataSource.addAnimal(gid, params, imageFile)

    override suspend fun loadMedical(aid: String): Result<List<Medical>> =
        animalRemoteDataSource.loadMedical(aid)

    override suspend fun getMemos(aid: String): Result<List<Memo>> =
        animalRemoteDataSource.getMemos(aid)

    override suspend fun addMedical(aid: String, params: Map<String, String>): Result<Void> =
        animalRemoteDataSource.addMedical(aid, params)

    override suspend fun getMemo(aid: String, mid: String): Result<Memo> =
        animalRemoteDataSource.getMemo(aid, mid)

    override suspend fun getMedical(hid: String): Result<Medical> =
        animalRemoteDataSource.getMedical(hid)

    override suspend fun updateMedical(hid: String, params: Map<String, String>): Result<Void> =
        animalRemoteDataSource.updateMedical(hid, params)

    override suspend fun setMemo(aid: String, gid: String, content: String): Result<Void> =
        animalRemoteDataSource.setMemo(aid, gid, content)

    override suspend fun deleteAnimal(aid: String): Result<Void> {
        TODO("Not yet implemented")
    }

    override suspend fun getAnimal(aid: String): Result<Animal> =
        animalRemoteDataSource.getAnimal(aid)

    override suspend fun updateAnimalDetail(
        aid: String,
        params: Map<String, String>,
        imageFile: MultipartBody.Part?
    ): Result<Void> =
        animalRemoteDataSource.updateAnimalDetail(aid, params, imageFile)

    override suspend fun updateMemo(mid: String, gid: String, content: String): Result<Void> =
        animalRemoteDataSource.updateMemo(mid, gid, content)

    override suspend fun deleteMemo(mid: String): Result<Void> =
        animalRemoteDataSource.deleteMemo(mid)
}