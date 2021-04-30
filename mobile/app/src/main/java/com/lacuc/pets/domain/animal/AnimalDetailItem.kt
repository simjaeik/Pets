package com.lacuc.pets.domain.animal

import com.lacuc.pets.data.animal.entity.Animal
import com.lacuc.pets.data.animal.entity.Medical
import com.lacuc.pets.data.animal.entity.Memo

sealed class AnimalDetailItem

class AnimalDetailDetailItem(animal: Animal) : AnimalDetailItem() {
    val name = animal.name
    val age = animal.age.toString()
    val sex = animal.sex
    val species = animal.species
    val subspecies = animal.subspecies
    val weight = animal.weight.toString()
    val number = animal.number
}

class AnimalDetailMedicalItem(medical: Medical) : AnimalDetailItem() {
    val title = medical.title
    val date = medical.date.toString()
    val hospital = medical.hospital
    val content = medical.content
}

class AnimalDetailMemoItem(memo: Memo) : AnimalDetailItem() {
    val content = memo.content
}