package com.lacuc.pets.data.animal

import javax.inject.Inject

class FakeAnimalRemoteDataSource @Inject constructor() : AnimalDataSource {

    private val animalData = mutableMapOf<Int, MutableList<Animal>>()

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
    }

    override fun loadAnimal(gid: Int): List<Animal> = animalData[gid] ?: emptyList()
}