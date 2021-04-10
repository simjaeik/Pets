package com.lacuc.pets.ui.animal.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lacuc.pets.domain.animal.GetMedicalUseCase
import com.lacuc.pets.domain.animal.GetMemoUseCase
import javax.inject.Inject

class AnimalDetailViewModel @Inject constructor(
    private val getMedicalUseCase: GetMedicalUseCase,
    private val getMemoUseCase: GetMemoUseCase,
) : ViewModel() {

    val detailItems = MutableLiveData<List<AnimalDetailItem>>()

    private lateinit var detailItem: List<AnimalDetailDetailItem>
    private val medicalItem: List<AnimalDetailMedicalItem> by lazy { getMedicalUseCase(1) }
    private val memoItem: List<AnimalDetailMemoItem> by lazy { getMemoUseCase(1) }

    fun initItem(item: AnimalDetailDetailItem) {
        if (detailItems.value.isNullOrEmpty()) {
            detailItem = listOf(item)
            detailItems.value = detailItem
        }
    }

    fun switchItem(position: Int) {
        when (position) {
            0 -> detailItems.value = detailItem
            1 -> detailItems.value = medicalItem
            2 -> detailItems.value = memoItem
        }
    }
}