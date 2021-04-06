package com.lacuc.pets.data.animal

class DefaultAnimalRepository(private val animalRemoteDataSource: AnimalDataSource) :
    AnimalRepository {
}