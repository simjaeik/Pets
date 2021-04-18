package com.lacuc.pets.data.animal

import com.lacuc.pets.data.animal.entity.Animal
import com.lacuc.pets.data.animal.entity.Medical
import com.lacuc.pets.data.animal.entity.Memo

interface AnimalDataSource {
    fun loadAnimal(gid: Int): List<Animal>

    fun addAnimal(animal: Animal)

    fun loadMedical(aid: Int): List<Medical>

    fun loadMemo(aid: Int): List<Memo>

    fun addMedical(medical: Medical)

    fun addMemo(memo: Memo)

}