package com.lacuc.pets.ui.group.add

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lacuc.pets.data.group.Group
import com.lacuc.pets.domain.group.AddGroupUseCase
import javax.inject.Inject

class AddGroupViewModel @Inject constructor(private val addGroupUseCase: AddGroupUseCase) :
    ViewModel() {

    val name = MutableLiveData("")

    val info = MutableLiveData("")

    val image = MutableLiveData("")

    val isShare = MutableLiveData(false)

    val groupUpdated = MutableLiveData<Boolean>()

    fun saveGroup() {
        addGroupUseCase(
            "tempUser@lacuc.com",
            Group(name.value ?: "", info.value ?: "", image.value ?: "", isShare.value ?: false)
        )
        groupUpdated.value = true
    }

    fun setImage(dataString: String?) {
        image.value = dataString
    }
}