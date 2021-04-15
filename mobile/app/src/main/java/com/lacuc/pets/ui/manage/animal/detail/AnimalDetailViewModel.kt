package com.lacuc.pets.ui.manage.animal.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lacuc.pets.domain.animal.AnimalDetailDetailItem
import com.lacuc.pets.domain.animal.AnimalDetailItem
import com.lacuc.pets.domain.animal.medical.GetMedicalUseCase
import com.lacuc.pets.domain.animal.memo.GetMemoUseCase
import javax.inject.Inject

class AnimalDetailViewModel @Inject constructor(
    private val getMedicalUseCase: GetMedicalUseCase,
    private val getMemoUseCase: GetMemoUseCase,
) : ViewModel() {

    val detailItems = MutableLiveData<List<AnimalDetailItem>>()

    private lateinit var detailItem: List<AnimalDetailDetailItem>
    private var medicalItem = getMedicalUseCase(1)
    private var memoItem = getMemoUseCase(1)

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

    fun refresh(position: Int) {
        when (position) {
            1 -> medicalItem = getMedicalUseCase(1)
            2 -> memoItem = getMemoUseCase(1)
        }
        switchItem(position)
    }
}