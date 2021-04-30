package com.lacuc.pets.data.animal

import com.lacuc.pets.data.Result
import com.lacuc.pets.data.animal.entity.Animal
import com.lacuc.pets.data.animal.entity.Memo
import retrofit2.http.*

interface AnimalService {
    @GET("api/animal/all/{id}")
    suspend fun getAnimalByGroup(@Path("id") gid: Int): Result<List<Animal>>

    @POST("api/animal")
    suspend fun addAnimal(@Body animal: Animal): Result<Void>

    @DELETE("api/animal/{id}")
    suspend fun deleteAnimal(@Path("id") aid: Int): Result<Void>

    @GET("api/animal/{id}")
    suspend fun getAnimal(@Path("id") aid: Int): Result<Animal>

    @PATCH("api/animal/{id}")
    suspend fun updateAnimalDetail(@Path("id") aid: Int, @Body animal: Animal): Result<Void>

    @GET("api/animal/memo/{id}")
    suspend fun getMemos(@Path("id") aid: Int): Result<List<Memo>>

    @POST("api/animal/memo/{id}")
    suspend fun setMemo(@Path("id") aid: Int, @Body memo: Memo): Result<Void>

    @PATCH("api/animal/memo/{id}")
    suspend fun updateMemo(@Path("id") mid: Int, @Body memo: Memo): Result<Void>

    @DELETE("api/animal/memo/{id}")
    suspend fun deleteMemo(@Path("id") mid: Int): Result<Void>
}