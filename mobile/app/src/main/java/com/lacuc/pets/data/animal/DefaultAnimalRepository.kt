package com.lacuc.pets.data.animal

import javax.inject.Inject

class DefaultAnimalRepository @Inject constructor(
    private val animalRemoteDataSource: AnimalDataSource
) : AnimalRepository {
    override fun loadAnimal(gid: Int): List<Animal> = animalRemoteDataSource.loadAnimal(gid)

    override fun addAnimal(animal: Animal) {
        animalRemoteDataSource.addAnimal(animal)
    }

    override fun loadMedical(aid: Int): List<Medical> = animalRemoteDataSource.loadMedical(aid)

    override fun loadMemo(aid: Int): List<Memo> = animalRemoteDataSource.loadMemo(aid)
}