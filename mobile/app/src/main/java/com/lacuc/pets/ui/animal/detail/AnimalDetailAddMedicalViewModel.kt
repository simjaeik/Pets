package com.lacuc.pets.ui.animal.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AnimalDetailAddMedicalViewModel : ViewModel() {

    val title = MutableLiveData<String>()
    val hospital = MutableLiveData<String>()
    val time = MutableLiveData<String>()
    val content = MutableLiveData<String>()
}