package com.lacuc.pets.data.animal

import com.lacuc.pets.data.Result
import com.lacuc.pets.data.animal.entity.Animal
import com.lacuc.pets.data.animal.entity.Medical
import com.lacuc.pets.data.animal.entity.Memo

interface AnimalDataSource {
    suspend fun getAnimalByGroup(gid: Int): Result<List<Animal>>

    suspend fun addAnimal(animal: Animal): Result<Void>

    suspend fun deleteAnimal(aid: Int): Result<Void>

    suspend fun getAnimal(aid: Int): Result<Animal>

    suspend fun updateAnimalDetail(aid: Int, animal: Animal): Result<Void>

    suspend fun getMemos(aid: Int): Result<List<Memo>>

    suspend fun setMemo(aid: Int, memo: Memo): Result<Void>

    suspend fun updateMemo(mid: Int, memo: Memo): Result<Void>

    suspend fun deleteMemo(mid: Int): Result<Void>

    suspend fun loadMedical(aid: Int): Result<List<Medical>>

    suspend fun addMedical(aid: Int, medical: Medical): Result<Void>
}