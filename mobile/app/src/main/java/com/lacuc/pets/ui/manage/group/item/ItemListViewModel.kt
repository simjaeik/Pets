package com.lacuc.pets.ui.manage.group.item

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lacuc.pets.data.Result
import com.lacuc.pets.domain.item.GetItemHistoryUseCase
import com.lacuc.pets.domain.item.ItemHistoryItem
import com.lacuc.pets.util.SingleLiveEvent
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class ItemListViewModel @Inject constructor(
    private val getItemHistoryUseCase: GetItemHistoryUseCase,
    private val errorEvent: SingleLiveEvent<String>
) : ViewModel() {

    var gid = -1
    val itemHistory = MutableLiveData<List<ItemHistoryItem>>()

    val itemClickEvent = SingleLiveEvent<ItemHistoryItem>()

    fun loadItems() {
        viewModelScope.launch {
            val items = getItemHistoryUseCase(gid) { itemClickEvent.value = it }

            when (items) {
                is Result.Success -> items.body?.let { itemHistory.value = it }
                is Result.Failure -> errorEvent.value =
                    "code: ${items.code} message: ${items.error}"
                is Result.NetworkError -> errorEvent.value = "네트워크 문제가 발생했습니다."
                is Result.Unexpected -> {
                    Timber.d(items.t.toString())
                    errorEvent.value = "알수없는 오류가 발생했습니다."
                }
            }
        }
    }
}