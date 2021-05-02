package com.lacuc.pets.ui.manage.group.info

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class UserProfileViewModel @Inject constructor(
    // TODO: 2021-05-02 val updateProfileUseCase
) : ViewModel() {
    val name = MutableLiveData("")
    val email = MutableLiveData("")

    val completeBtnEnable = MutableLiveData(false)

    fun updateProfile() {
        // TODO: 2021-05-02 call updateProfileUseCase
    }
}