package com.lacuc.pets.ui.manage.animal.detail.memo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lacuc.pets.data.animal.Memo
import com.lacuc.pets.domain.animal.memo.AddMemoUseCase
import com.lacuc.pets.util.SingleLiveEvent
import com.lacuc.pets.util.safeValue
import javax.inject.Inject

class AddMemoViewModel @Inject constructor(val addMemoUseCase: AddMemoUseCase) :
    ViewModel() {

    val content = MutableLiveData("")

    val completeEvent = SingleLiveEvent<Unit>()

    fun onCompleteClick() {
        addMemoUseCase(Memo(content.safeValue))
        completeEvent.value = Unit
    }
}