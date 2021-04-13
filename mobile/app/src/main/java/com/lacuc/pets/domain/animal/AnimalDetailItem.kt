package com.lacuc.pets.domain.animal

import com.lacuc.pets.data.animal.Animal
import com.lacuc.pets.data.animal.Medical
import com.lacuc.pets.data.animal.Memo

sealed class AnimalDetailItem

class AnimalDetailDetailItem(private val animal: Animal) : AnimalDetailItem() {
    fun getName() = animal.name
    fun getAge() = animal.age.toString()
    fun getSex() = animal.sex
    fun getSpecies() = animal.species
    fun getSubspecies() = animal.subspecies
    fun getWeight() = animal.weight.toString()
    fun getNumber() = animal.number
}

class AnimalDetailMedicalItem(private val medical: Medical) : AnimalDetailItem() {
    fun getTitle() = medical.title
    fun getDate() = medical.date.toString()
    fun getHospital() = medical.hospital
    fun getContent() = medical.content
}

class AnimalDetailMemoItem(private val memo: Memo) : AnimalDetailItem() {
    fun getContent() = memo.content
}