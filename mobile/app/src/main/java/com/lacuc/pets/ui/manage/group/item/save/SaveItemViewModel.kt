package com.lacuc.pets.ui.manage.group.item.save

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lacuc.pets.data.Result
import com.lacuc.pets.domain.item.AddItemUseCase
import com.lacuc.pets.domain.item.GetItemUseCase
import com.lacuc.pets.domain.item.UpdateItemUseCase
import com.lacuc.pets.util.SingleLiveEvent
import com.lacuc.pets.util.safeValue
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class SaveItemViewModel @Inject constructor(
    private val getItemUseCase: GetItemUseCase,
    private val addItemUseCase: AddItemUseCase,
    private val updateItemUseCase: UpdateItemUseCase,
    private val errorEvent: SingleLiveEvent<String>
) : ViewModel() {

    var hid = ""
    var gid = ""

    val name = MutableLiveData("")
    val category = MutableLiveData("")
    val link = MutableLiveData("")
    val price = MutableLiveData("")

    var isUpdate = false

    val completeEvent = SingleLiveEvent<Unit>()

    fun loadItem() {
        viewModelScope.launch {
            val item = getItemUseCase()

            when (item) {
                is Result.Success -> item.body?.let {
                    isUpdate = true
                    name.value = it.name
                    category.value = it.category
                    link.value = it.link
                    price.value = it.price
                }
                is Result.Failure -> errorEvent.value =
                    "code: ${item.code} message: ${item.error}"
                is Result.NetworkError -> errorEvent.value = "네트워크 문제가 발생했습니다."
                is Result.Unexpected -> {
                    Timber.d(item.t.toString())
                    errorEvent.value = "알수없는 오류가 발생했습니다."
                }
            }
        }
    }

    fun saveItem() {
        viewModelScope.launch {
            val result = if (isUpdate)
                updateItemUseCase(hid, createParams())
            else
                addItemUseCase(createParams())

            when (result) {
                is Result.Success -> completeEvent.value = Unit
                is Result.Failure -> errorEvent.value =
                    "code: ${result.code} message: ${result.error}"
                is Result.NetworkError -> errorEvent.value = "네트워크 문제가 발생했습니다."
                is Result.Unexpected -> {
                    Timber.d(result.t.toString())
                    errorEvent.value = "알수없는 오류가 발생했습니다."
                }
            }
        }
    }

    private fun createParams(): Map<String, String> = mapOf(
        "GID" to gid,
        "name" to name.safeValue,
        "category" to category.safeValue,
        "link" to link.safeValue,
        "price" to price.safeValue
    )
}