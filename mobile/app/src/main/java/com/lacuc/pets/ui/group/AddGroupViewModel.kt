package com.lacuc.pets.ui.group

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lacuc.pets.domain.group.AddGroupUseCase
import javax.inject.Inject

class AddGroupViewModel @Inject constructor(private val addGroupUseCase: AddGroupUseCase) :
    ViewModel() {

    val name = MutableLiveData<String>()

    val info = MutableLiveData<String>()

    val image = MutableLiveData("")

    val isShare = MutableLiveData(false)

    fun saveGroup() {

    }
}