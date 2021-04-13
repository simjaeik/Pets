package com.lacuc.pets.data.animal

interface AnimalDataSource {
    fun loadAnimal(gid: Int): List<Animal>

    fun addAnimal(animal: Animal)

    fun loadMedical(aid: Int): List<Medical>

    fun loadMemo(aid: Int): List<Memo>

    fun addMedical(medical: Medical)

}