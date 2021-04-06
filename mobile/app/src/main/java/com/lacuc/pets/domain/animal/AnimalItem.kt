package com.lacuc.pets.domain.animal

import com.lacuc.pets.data.animal.Animal

class AnimalItem(val animal: Animal, val clickListener: (AnimalItem) -> Unit) {

}
