package com.lacuc.pets.data.animal

import com.lacuc.pets.data.Result
import com.lacuc.pets.data.animal.entity.Animal
import com.lacuc.pets.data.animal.entity.Medical
import com.lacuc.pets.data.animal.entity.Memo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FakeAnimalRemoteDataSource @Inject constructor() : AnimalDataSource {

    private val animalData = mutableMapOf<Int, List<Animal>>()

    private val medicalData = mutableMapOf<Int, List<Medical>>()

    private val memoData = mutableMapOf<Int, List<Memo>>()

    init {
        animalData[1] = listOf(
            Animal(
                1,
                "보리",
                "https://images.unsplash.com/photo-1495360010541-f48722b34f7d?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1276&q=80",
                2,
                "male",
                "고양이",
                "아비시니안",
                2.1,
                "1-1"
            ),
            Animal(
                1,
                "우주",
                "https://images.unsplash.com/photo-1572632821054-b1bb7d7010c0?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80",
                2,
                "male",
                "고양이",
                "노르웨이 숲 고양이",
                5.8,
                "1-2"
            ),
        )

        medicalData[1] = listOf(
            Medical(100000, "중성화 수술", "우주 돈까스 먹으로 가는 날", "장산 동물 병원"),
            Medical(200000, "정기 건강검진", "우주 주사 맞으러 가는 날", "장산 동물 병원"),
        )

        memoData[1] = listOf(
            Memo("고양이 모래 사야됨"),
            Memo("캣타워 사야됨"),
        )
    }

    override suspend fun addAnimal(animal: Animal): Result<Void> = withContext(Dispatchers.IO) {
        delay(100)
        animalData[animal.gid] = animalData.getOrPut(animal.gid) { listOf() } + animal
        Result.Success(null)
    }

    override suspend fun loadMedical(aid: Int): Result<List<Medical>> =
        withContext(Dispatchers.IO) {
            delay(100)
            Result.Success(medicalData[aid] ?: emptyList())
        }

    override suspend fun addMedical(aid: Int, medical: Medical): Result<Void> =
        withContext(Dispatchers.IO) {
            delay(100)
            medicalData[aid] = medicalData.getOrPut(aid) { listOf() } + medical
            Result.Success(null)
        }

    override suspend fun getAnimalByGroup(gid: Int): Result<List<Animal>> =
        withContext(Dispatchers.IO) {
            delay(100)
            Result.Success(animalData[gid] ?: emptyList())
        }


    override suspend fun deleteAnimal(aid: Int): Result<Void> {
        TODO("Not yet implemented")
    }

    override suspend fun getAnimal(aid: Int): Result<Animal> {
        TODO("Not yet implemented")
    }

    override suspend fun updateAnimalDetail(aid: Int, animal: Animal): Result<Void> {
        TODO("Not yet implemented")
    }

    override suspend fun getMemos(aid: Int): Result<List<Memo>> = withContext(Dispatchers.IO) {
        delay(100)
        Result.Success(memoData[aid] ?: emptyList())
    }

    override suspend fun setMemo(aid: Int, memo: Memo): Result<Void> = withContext(Dispatchers.IO) {
        delay(100)
        memoData[aid] = memoData.getOrPut(aid) { listOf() } + memo
        Result.Success(null)
    }

    override suspend fun updateMemo(mid: Int, memo: Memo): Result<Void> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteMemo(mid: Int): Result<Void> {
        TODO("Not yet implemented")
    }
}