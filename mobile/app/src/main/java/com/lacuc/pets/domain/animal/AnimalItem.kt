package com.lacuc.pets.domain.animal

import com.lacuc.pets.data.animal.entity.Animal

class AnimalItem(val animal: Animal, val clickListener: (AnimalItem) -> Unit) {
    val aid = animal.AID
    val name = animal.name
    val species = animal.species
    val subspecies = animal.subspecies
    val image = animal.image
    val age = "${animal.age}세"
    val sex = animal.sex
    val weight = animal.weight.toString()
    val number = animal.number
}
