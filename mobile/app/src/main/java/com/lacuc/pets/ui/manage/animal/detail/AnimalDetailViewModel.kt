package com.lacuc.pets.ui.manage.animal.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lacuc.pets.data.Result
import com.lacuc.pets.domain.animal.AnimalDetailDetailItem
import com.lacuc.pets.domain.animal.AnimalDetailItem
import com.lacuc.pets.domain.animal.medical.GetMedicalUseCase
import com.lacuc.pets.domain.animal.memo.GetMemoUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class AnimalDetailViewModel @Inject constructor(
    private val getMedicalUseCase: GetMedicalUseCase,
    private val getMemoUseCase: GetMemoUseCase,
) : ViewModel() {

    val detailItems = MutableLiveData<List<AnimalDetailItem>>()

    val loading = MutableLiveData(false)

    private lateinit var detailItem: List<AnimalDetailDetailItem>

    fun initItem(item: AnimalDetailDetailItem) {
        if (detailItems.value.isNullOrEmpty()) {
            detailItem = listOf(item)
            detailItems.value = detailItem
        }
    }

    fun loadDetailItem(position: Int) {
        viewModelScope.launch {
            loading.value = true
            val itemList = when (position) {
                0 -> Result.Success(detailItem)
                1 -> getMedicalUseCase(1)
                2 -> getMemoUseCase(1)
                else -> {
                    loading.value = false
                    return@launch
                }
            }
            loading.value = false

            when (itemList) {
                is Result.Success -> itemList.body?.let { detailItems.value = it }
                else -> TODO("Not Implemented")
            }
        }
    }
}