package com.lacuc.pets.data.animal

interface AnimalDataSource {
    fun loadAnimal(gid: Int): List<Animal>

    fun addAnimal(animal: Animal)
}