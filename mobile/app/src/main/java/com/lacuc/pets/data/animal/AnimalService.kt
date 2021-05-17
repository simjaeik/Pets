package com.lacuc.pets.data.animal

import com.lacuc.pets.data.Result
import com.lacuc.pets.data.animal.entity.Animal
import com.lacuc.pets.data.animal.entity.Memo
import retrofit2.http.*

interface AnimalService {
    @GET("api/animal/all/{id}")
    suspend fun getAnimalByGroup(@Path("id") gid: String): Result<List<Animal>>

    @POST("api/animal")
    suspend fun addAnimal(@Body animal: Animal): Result<Void>

    @DELETE("api/animal/{id}")
    suspend fun deleteAnimal(@Path("id") aid: String): Result<Void>

    @GET("api/animal/{id}")
    suspend fun getAnimal(@Path("id") aid: String): Result<Animal>

    @PATCH("api/animal/{id}")
    suspend fun updateAnimalDetail(@Path("id") aid: String, @Body animal: Animal): Result<Void>

    @GET("api/animal/memo/{id}")
    suspend fun getMemos(@Path("id") aid: String): Result<List<Memo>>

    @POST("api/animal/memo/{id}")
    suspend fun setMemo(@Path("id") aid: String, @Body memo: Memo): Result<Void>

    @FormUrlEncoded
    @PATCH("api/animal/memo/{id}")
    suspend fun updateMemo(
        @Path("id") mid: String,
        @Field("GID") gid: String,
        @Field("content") content: String
    ): Result<Void>

    @DELETE("api/animal/memo/{id}")
    suspend fun deleteMemo(@Path("id") mid: String): Result<Void>
}