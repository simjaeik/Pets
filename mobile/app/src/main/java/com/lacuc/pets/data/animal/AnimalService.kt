package com.lacuc.pets.data.animal

import com.lacuc.pets.data.animal.entity.Animal
import com.lacuc.pets.data.animal.entity.Memo
import retrofit2.http.*

interface AnimalService {
    @GET("api/animal/all/{id}")
    fun getAnimalByGroup(@Path("id") id: Int)

    @POST("api/animal")
    fun addAnimal(@Body animal: Animal)

    @DELETE("api/animal/{id}")
    fun deleteAnimal(@Path("id") id: Int)

    @GET("api/animal/{id}")
    fun getAnimal(@Path("id") id: Int)

    @PATCH("api/animal/{id}")
    fun updateAnimalDetail(@Path("id") id: Int, @Body animal: Animal)

    @GET("api/animal/memo/{id}")
    fun getMemos(@Path("id") id: Int)

    @POST("api/animal/memo/{id}")
    fun setMemo(@Path("id") id: Int, @Body memo: Memo)

    @PATCH("api/animal/memo/{id}")
    fun updateMemo(@Path("id") id: Int, @Body memo: Memo)

    @DELETE("api/animal/memo/{id}")
    fun deleteMemo(@Path("id") id: Int)
}