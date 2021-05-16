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

    private var animalData = mutableListOf<Animal>()
    private var medicalData = mutableListOf<Medical>()
    private var memoData = mutableListOf<Memo>()

    init {
        initAnimal()
        initMedical()
        initMemo()
    }

    override suspend fun getAnimalByGroup(gid: String): Result<List<Animal>> =
        withContext(Dispatchers.IO) {
            delay(100)
            Result.Success(animalData)
        }

    override suspend fun addAnimal(animal: Animal): Result<Void> = withContext(Dispatchers.IO) {
        delay(100)
        animalData = (animalData + animal) as MutableList<Animal>
        Result.Success(null)
    }

    override suspend fun deleteAnimal(aid: String): Result<Void> {
        TODO("Not yet implemented")
    }

    override suspend fun getAnimal(aid: String): Result<Animal> = withContext(Dispatchers.IO) {
        Result.Success(animalData.find { it.aid == aid })
    }

    override suspend fun updateAnimalDetail(aid: String, animal: Animal): Result<Void> =
        withContext(Dispatchers.IO) {
            animalData = (animalData.filter { it.aid != aid } + animal) as MutableList<Animal>
            Result.Success(null)
        }

    override suspend fun getMemos(aid: String): Result<List<Memo>> = withContext(Dispatchers.IO) {
        delay(100)
        Result.Success(memoData)
    }

    override suspend fun setMemo(aid: String, memo: Memo): Result<Void> =
        withContext(Dispatchers.IO) {
            delay(100)
            memoData = (memoData + memo) as MutableList<Memo>
            Result.Success(null)
        }


    override suspend fun updateMemo(mid: String, memo: Memo): Result<Void> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteMemo(mid: String): Result<Void> {
        TODO("Not yet implemented")
    }

    override suspend fun loadMedical(aid: String): Result<List<Medical>> =
        withContext(Dispatchers.IO) {
            delay(100)
            Result.Success(medicalData)
        }

    override suspend fun addMedical(aid: String, medical: Medical): Result<Void> =
        withContext(Dispatchers.IO) {
            delay(100)
            medicalData = (medicalData + medical) as MutableList<Medical>
            Result.Success(null)
        }

    private fun initAnimal() {
        animalData = mutableListOf(
            Animal(
                "0",
                "0",
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
                "1",
                "0",
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
    }

    private fun initMedical() {
        medicalData = mutableListOf(
            Medical(100000, "중성화 수술", "우주 돈까스 먹으로 가는 날", "장산 동물 병원"),
            Medical(200000, "정기 건강검진", "우주 주사 맞으러 가는 날", "장산 동물 병원"),
        )
    }

    private fun initMemo() {
        memoData = mutableListOf(
            Memo("고양이 모래 사야됨"),
            Memo("캣타워 사야됨"),
        )
    }
}