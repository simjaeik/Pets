package com.lacuc.pets.data.animal

import com.lacuc.pets.data.Result
import com.lacuc.pets.data.animal.entity.Animal
import com.lacuc.pets.data.animal.entity.Medical
import com.lacuc.pets.data.animal.entity.Memo
import okhttp3.MultipartBody

interface AnimalRepository {

    suspend fun getAnimalByGroup(gid: String): Result<List<Animal>>

    suspend fun addAnimal(
        gid: String, params: Map<String, String>,
        imageFile: MultipartBody.Part
    ): Result<Void>

    suspend fun deleteAnimal(aid: String): Result<Void>

    suspend fun getAnimal(aid: String): Result<Animal>

    suspend fun updateAnimalDetail(
        aid: String,
        params: Map<String, String>,
        imageFile: MultipartBody.Part? = null
    ): Result<Void>

    suspend fun getMemos(aid: String): Result<List<Memo>>

    suspend fun setMemo(aid: String, gid: String, content: String): Result<Void>

    suspend fun updateMemo(mid: String, gid: String, content: String): Result<Void>

    suspend fun deleteMemo(mid: String): Result<Void>

    suspend fun loadMedical(aid: String): Result<List<Medical>>

    suspend fun addMedical(aid: String, params: Map<String, String>): Result<Void>

    suspend fun getMemo(mid: String): Result<Memo>

    suspend fun getMedical(hid: String): Result<Medical>

    suspend fun updateMedical(hid: String, params: Map<String, String>): Result<Void>
}