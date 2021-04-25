package com.lacuc.pets.ui.manage.group.add

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lacuc.pets.data.group.entity.Group
import com.lacuc.pets.domain.group.AddGroupUseCase
import com.lacuc.pets.util.SingleLiveEvent
import com.lacuc.pets.util.safeValue
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddGroupViewModel @Inject constructor(private val addGroupUseCase: AddGroupUseCase) :
    ViewModel() {

    val name = MutableLiveData("")
    val info = MutableLiveData("")
    val image = MutableLiveData("")
    val isShare = MutableLiveData(false)

    val completeEvent = SingleLiveEvent<Unit>()

    fun saveGroup() {
        viewModelScope.launch {
            addGroupUseCase(
                Group(name.safeValue, info.safeValue, image.safeValue, isShare.safeValue)
            )
            completeEvent.value = Unit
        }
    }

    fun setImage(dataString: String?) {
        image.value = dataString
    }
}