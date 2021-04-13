package com.lacuc.pets.ui.animal.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lacuc.pets.data.animal.Memo
import com.lacuc.pets.domain.animal.memo.AddMemoUseCase
import com.lacuc.pets.util.SingleLiveEvent
import com.lacuc.pets.util.safeValue
import javax.inject.Inject

class AnimalDetailAddMemoViewModel @Inject constructor(val addMemoUseCase: AddMemoUseCase) :
    ViewModel() {

    val content = MutableLiveData("")

    val completeEvent = SingleLiveEvent<Boolean>()

    fun onCompleteClick() {
        addMemoUseCase(Memo(content.safeValue))
        completeEvent.value = true
    }
}