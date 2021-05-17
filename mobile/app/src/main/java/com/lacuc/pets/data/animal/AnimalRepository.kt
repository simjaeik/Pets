package com.lacuc.pets.data.animal

import com.lacuc.pets.data.Result
import com.lacuc.pets.data.animal.entity.Animal
import com.lacuc.pets.data.animal.entity.Medical
import com.lacuc.pets.data.animal.entity.Memo

interface AnimalRepository {

    suspend fun getAnimalByGroup(gid: String): Result<List<Animal>>

    suspend fun addAnimal(animal: Animal): Result<Void>

    suspend fun deleteAnimal(aid: String): Result<Void>

    suspend fun getAnimal(aid: String): Result<Animal>

    suspend fun updateAnimalDetail(aid: String, animal: Animal): Result<Void>

    suspend fun getMemos(aid: String): Result<List<Memo>>

    suspend fun setMemo(aid: String, memo: Memo): Result<Void>

    suspend fun updateMemo(mid: String, memo: Memo): Result<Void>

    suspend fun deleteMemo(mid: String): Result<Void>

    suspend fun loadMedical(aid: String): Result<List<Medical>>

    suspend fun addMedical(aid: String, medical: Medical): Result<Void>

    suspend fun getMemo(aid: String, mid: String): Result<Memo>

}