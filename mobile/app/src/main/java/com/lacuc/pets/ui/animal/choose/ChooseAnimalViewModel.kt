package com.lacuc.pets.ui.animal.choose

import androidx.lifecycle.ViewModel
import com.lacuc.pets.domain.animal.GetAnimalUseCase
import javax.inject.Inject

class ChooseAnimalViewModel @Inject constructor(
    val getAnimalUseCase: GetAnimalUseCase
) : ViewModel() {

}