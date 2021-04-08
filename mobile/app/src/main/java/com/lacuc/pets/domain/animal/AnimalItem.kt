package com.lacuc.pets.domain.animal

import com.lacuc.pets.data.animal.Animal

class AnimalItem(val animal: Animal, val clickListener: (AnimalItem) -> Unit) {

    fun getName() = animal.name

    fun getSubspecies() = animal.subspecies

    fun getImage() = animal.image

    fun getAge() = "${animal.age}세"

    fun getSex() = animal.sex

    fun getWeight() = animal.weight.toString()

}
