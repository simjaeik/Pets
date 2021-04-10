package com.lacuc.pets.ui.animal.detail

import com.lacuc.pets.data.animal.Animal
import com.lacuc.pets.data.animal.Medical
import com.lacuc.pets.data.animal.Memo

sealed class AnimalDetailItem

class AnimalDetailMedicalItem(val medical: Medical) : AnimalDetailItem()

class AnimalDetailMemoItem(val memo: Memo) : AnimalDetailItem()

class AnimalDetailDetailItem(val animal: Animal) : AnimalDetailItem()