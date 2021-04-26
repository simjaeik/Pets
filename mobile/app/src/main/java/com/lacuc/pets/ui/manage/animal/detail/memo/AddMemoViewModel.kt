package com.lacuc.pets.ui.manage.animal.detail.memo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lacuc.pets.data.Result
import com.lacuc.pets.data.animal.entity.Memo
import com.lacuc.pets.domain.animal.memo.AddMemoUseCase
import com.lacuc.pets.util.SingleLiveEvent
import com.lacuc.pets.util.safeValue
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddMemoViewModel @Inject constructor(val addMemoUseCase: AddMemoUseCase) :
    ViewModel() {

    val content = MutableLiveData("")

    val completeEvent = SingleLiveEvent<Unit>()

    fun onCompleteClick() {
        viewModelScope.launch {
            val result = addMemoUseCase(1, Memo(content.safeValue))

            when (result) {
                is Result.Success -> completeEvent.value = Unit
                else -> TODO("Not Implemented")
            }
        }
    }
}