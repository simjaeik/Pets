package com.lacuc.pets.ui.group

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddGroupViewModel() : ViewModel() {

    val name = MutableLiveData<String>()

    val info = MutableLiveData<String>()

    val image = MutableLiveData<String>()

    val isShare = MutableLiveData(false)

    fun saveGroup() {

    }
}