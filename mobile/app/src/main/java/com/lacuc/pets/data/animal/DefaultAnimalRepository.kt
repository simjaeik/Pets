package com.lacuc.pets.data.animal

import com.lacuc.pets.data.animal.entity.Animal
import com.lacuc.pets.data.animal.entity.Medical
import com.lacuc.pets.data.animal.entity.Memo
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

    override fun addMedical(medical: Medical) {
        animalRemoteDataSource.addMedical(medical)
    }

    override fun addMemo(memo: Memo) {
        animalRemoteDataSource.addMemo(memo)
    }
}