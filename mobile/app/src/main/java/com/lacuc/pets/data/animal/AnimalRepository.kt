package com.lacuc.pets.data.animal

interface AnimalRepository {
    fun loadAnimal(gid: Int): List<Animal>

    fun addAnimal(animal: Animal)
}