package com.lacuc.pets.ui.animal.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class AnimalDetailViewModel @Inject constructor() : ViewModel() {

    val detailItems = MutableLiveData<List<AnimalDetailItem>>()

    fun initItem(item: AnimalDetailItem) {
        if (detailItems.value.isNullOrEmpty()) {
            detailItems.value = listOf(item)
        }
    }
}