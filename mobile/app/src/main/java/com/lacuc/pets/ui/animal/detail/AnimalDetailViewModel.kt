package com.lacuc.pets.ui.animal.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lacuc.pets.domain.animal.AnimalDetailDetailItem
import com.lacuc.pets.domain.animal.AnimalDetailItem
import com.lacuc.pets.domain.animal.AnimalDetailMedicalItem
import com.lacuc.pets.domain.animal.AnimalDetailMemoItem
import com.lacuc.pets.domain.animal.medical.GetMedicalUseCase
import com.lacuc.pets.domain.animal.memo.GetMemoUseCase
import javax.inject.Inject

class AnimalDetailViewModel @Inject constructor(
    private val getMedicalUseCase: GetMedicalUseCase,
    private val getMemoUseCase: GetMemoUseCase,
) : ViewModel() {

    val detailItems = MutableLiveData<List<AnimalDetailItem>>()

    private lateinit var detailItem: List<AnimalDetailDetailItem>
    private lateinit var medicalItem: List<AnimalDetailMedicalItem>
    private lateinit var memoItem: List<AnimalDetailMemoItem>

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

    fun refresh() {
        medicalItem = getMedicalUseCase(1)
        memoItem = getMemoUseCase(1)
    }
}