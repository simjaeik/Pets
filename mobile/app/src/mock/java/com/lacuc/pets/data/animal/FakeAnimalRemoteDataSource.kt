package com.lacuc.pets.data.animal

import javax.inject.Inject

class FakeAnimalRemoteDataSource @Inject constructor() : AnimalDataSource {

    private val animalData = mutableMapOf<Int, MutableList<Animal>>()

    private val medicalData = mutableMapOf<Int, MutableList<Medical>>()

    private val memoData = mutableMapOf<Int, MutableList<Memo>>()

    init {
        animalData[1] = mutableListOf(
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

        medicalData[1] = mutableListOf(
            Medical(100000, "중성화 수술", "우주 돈까스 먹으로 가는 날", "장산 동물 병원"),
            Medical(200000, "정기 건강검진", "우주 주사 맞으러 가는 날", "장산 동물 병원"),
        )

        memoData[1] = mutableListOf(
            Memo("고양이 모래 사야됨"),
            Memo("캣타워 사야됨"),
        )
    }

    override fun loadAnimal(gid: Int): List<Animal> = animalData[gid] ?: emptyList()

    override fun addAnimal(animal: Animal) {
        animalData.getOrPut(animal.gid) { mutableListOf() }.add(animal)
    }

    override fun loadMedical(aid: Int): List<Medical> = medicalData[aid] ?: emptyList()

    override fun loadMemo(aid: Int): List<Memo> = memoData[aid] ?: emptyList()

    override fun addMedical(medical: Medical) {
        medicalData[1]?.add(medical)
    }
}